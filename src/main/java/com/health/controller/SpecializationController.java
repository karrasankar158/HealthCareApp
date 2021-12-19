package com.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.health.entity.Specialization;
import com.health.exception.SpecializationNotFoundException;
import com.health.service.ISpecializationService;
import com.health.view.SpecializationCSVExportView;
import com.health.view.SpecializationExcelExportView;
import com.health.view.SpecializationPDFExportView;

@Controller
@RequestMapping("/spec")
public class SpecializationController {

	@Autowired
	private ISpecializationService service;

	@Autowired
	private SpecializationCSVExportView view;

	/**
	 * 1. Show register page 
	 */
	@GetMapping("/register")
	public String displayregister() {
		return "SpecializationRegister";
	}

	/**
	 * 2. On submit form save data
	 */
	@PostMapping("/save")
	public String saveForm(
			@ModelAttribute Specialization specialization, Model model
			) {
		Long id=service.saveSpecialization(specialization);

		String message="Record ["+id+"] is created";
		model.addAttribute("message",message);
		return "SpecializationRegister";
	}

	/**
	 * 3.Display All Specializations
	 */
	@GetMapping("/all")
	public String viewAll(
			Model model,@RequestParam(value="message", required = false) String message
			) {

		List<Specialization> list=service.getAllSepcializations();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
		return "SpecializationData";
	}
	/**
	 * 4. Delete by id
	 */
	@GetMapping("/delete")
	public String deleteData(
			@RequestParam Long id,
			RedirectAttributes attributes
			) {
		try {

			service.removeSpecialization(id);
			attributes.addAttribute("message","Record ["+id+"] is deleted");

		} catch (SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message",e.getMessage());
		}
		return "redirect:all";
	}

	/**
	 * 5. Fetch data into Edit page
	 * End user clicks on link, may enter ID manually.
	 * if entered ID is present in Database 
	 *      > Load Row as object.
	 *      > Send to SpecializationEdit page
	 *      >Fill in Form
	 *  Else
	 *      >Redirect to all (redirect:all) to SpecializationData page
	 *      >Show error message(ID Not Found).
	 */
	@GetMapping("/edit")
	public String showEditPage(
			@RequestParam Long id,
			Model model,
			RedirectAttributes attributes
			) {

		String page=null;
		try {

			Specialization spec=service.getOneSpecialization(id);
			model.addAttribute("specialization",spec);
			page="SpecializationEdit";
		} catch (SpecializationNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message",e.getMessage());
			page="redirect:all";
		}
		return page;
	}
	/**
	 * 6. Update form data and Re-Direct to all
	 */
	@PostMapping("/update")
	public String updateData(
			@ModelAttribute Specialization specialization,
			RedirectAttributes attributes
			) {
		service.updateSpecialization(specialization);
		attributes.addAttribute("message","Record ["+specialization.getId()+"] is Updated");
		return "redirect:all";
	}
	/**
	 * 7. Read code and check with Service
	 * return message back to UI
	 */
	@GetMapping("/checkCode")
	//@ResponseBody //or
	public @ResponseBody String validateSpecCode(
			@RequestParam String code,
			@RequestParam Long id
			) {
		String message="";
		if(id==0 && service.isSpecCodeExist(code)) {//Register check
			message= code+",  Already exist";
		}else if(id!=0 && service.isSpecCodeExistForEdit(code,id)) {//Edit check
			message= code+",  Already exist";
		}
		return message;//this is not viewName(it is message)
	}
	/**
	 * 8. Read name and check with Service
	 * return message back to UI
	 */
	@GetMapping("/checkName")
	//@ResponseBody //or
	public @ResponseBody String validateSpecName(
			@RequestParam String name,
			@RequestParam Long id
			) {
		String message="";
		if(id==0 && service.isSpecNameExist(name)) {//Register check
			message= name+",  Already exist";
		} else if(id!=0 && service.isSpecNameExistForEdit(name,id)) {//Edit check
			message= name+",  Already exist";
		} 
		return message;//this is not viewName(it is message)
	}

	/**
	 * 8. Export data to excel file
	 */
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView m=new ModelAndView();
		m.setView(new SpecializationExcelExportView());

		//Read data from Database
		List<Specialization> list=service.getAllSepcializations();

		//Send to Excel implementation class
		m.addObject("list",list);
		return m;
	}
	/**
	 * 9. Export data to PDF file
	 */
	@GetMapping("/pdf")
	public ModelAndView exportToPDF() {
		ModelAndView m=new ModelAndView();
		m.setView(new SpecializationPDFExportView());

		//Read data from Database
		List<Specialization> list=service.getAllSepcializations();

		//Send to PDF implementation class
		m.addObject("list",list);
		return m;
	}

	/**
	 * 10. Export data to CSV file
	 */
	@GetMapping("/csv")
	@ResponseBody
	public  void exportToCSV() {
		view.exportToCSV();
	}


}
