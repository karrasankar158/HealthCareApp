package com.health.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
//import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.health.entity.Doctor;
import com.health.exception.DoctorNotFoundException;
import com.health.service.IDoctorService;
import com.health.service.ISpecializationService;
//import com.health.util.FileUploadUtil;
/**
 * Doctor Controller 
 * @author Sankar Karra
 *
 */
@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private IDoctorService service;

	//for module integration
	@Autowired
	private ISpecializationService speService;

	/**
	 * For Integration purpose 
	 */
	private void createDynamicUI(
			Model model
			){
		model.addAttribute("specializations", speService.getSpecIdAndName());
	}

	/**
	 * 1. Show register page
	 */
	@GetMapping("/register")
	public String showRegister(
			@RequestParam(value = "message",required = false) String message,
			Model model
			) {
		model.addAttribute("message",message);
		//calling Module integration method
		createDynamicUI(model);
		return "DoctorRegister";
	}

	/**
	 * 2. Save on submit
	 */
	@PostMapping("/save")
	public String save(
			@ModelAttribute Doctor doctor,
			RedirectAttributes attributes
			) {
		Long id=service.saveDoctor(doctor);
		attributes.addAttribute("message","Doctor ( "+id+" ) is created");
		return "redirect:register";
	}

	//	/**
	//	 * For Not Recommended case-1
	//	 * 2. Save on submit
	//	 */
	//	@PostMapping("/save")
	//	public String save(
	//			@ModelAttribute Doctor doctor,
	//			@RequestParam("docImage") MultipartFile multipartFile,
	//			RedirectAttributes attributes
	//			) {
	//
	//		String fileName=StringUtils.cleanPath(multipartFile.getOriginalFilename());
	//		
	//		doctor.setPhotos(fileName);
	//		Long id=service.saveDoctor(doctor);
	//		attributes.addAttribute("message","Doctor ( "+id+" ) is created");
	//		
	//		String uploadDir="user-photos/"+id;
	//		try {
	//			FileUploadUtil.saveFile(uploadDir, fileName,multipartFile);
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		
	//		return "redirect:register";
	//	}

	/**
	 * 3. Display Doctors data
	 */
	@GetMapping("/all")
	public String display(@RequestParam(value = "message",required = false) String message,
			Model model
			) {

		List<Doctor> list=service.getAllDoctors();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
		return "DoctorData";
	}

	/**
	 * 4. Delete by id
	 */
	@GetMapping("/delete")
	public String delete(
			@RequestParam("id") Long id,
			@ModelAttribute Doctor doctor,
			RedirectAttributes attributes
			) {
		String message=null;
		try {
			service.removeDoctor(id);
			message=doctor.getId()+", Doctor removed";
		}
		catch(DoctorNotFoundException e) {
			e.printStackTrace();
			message=e.getMessage();
		}
		attributes.addAttribute("message",message);
		return "redirect:all";
	}

	/**
	 * 5. Show edit
	 */
	@GetMapping("/edit")
	public String edit(
			@RequestParam("id") Long id,
			Model model,
			RedirectAttributes attributes
			) {
		String page=null;
		try {

			//for image Display in Edit page
			List<Doctor> list=service.getAllDoctors();
			model.addAttribute("list",list);

			Doctor doc=service.getOneDoctor(id);
			model.addAttribute("doctor",doc);

			//calling Module integration method
			createDynamicUI(model);
			page="DoctorEdit";
		}
		catch(DoctorNotFoundException e) {
			e.printStackTrace();
			attributes.addAttribute("message",e.getMessage());
			page="redirect:all";
		}
		return page;
	}
	/**
	 * 6. Do update
	 */
	@PostMapping("/update")
	public String doUpdate(
			@ModelAttribute Doctor doctor,
			RedirectAttributes attributes
			) {
		service.updateDoctor(doctor);
		attributes.addAttribute("message",doctor.getId()+ ", updated!");
		return "redirect:all";
	}
	/**
	 * 7. Email and mobile duplicate validations(AJAX)
	 */

	/**
	 * 8. Excel Export
	 */

	/**
	 * 9. PDF Export
	 */
}
