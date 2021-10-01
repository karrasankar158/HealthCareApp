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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.health.entity.Doctor;
import com.health.exception.DoctorNotFoundException;
import com.health.service.IDoctorService;
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

	/**
	 * 1. Show register page
	 */
	@GetMapping("/register")
	public String showRegister(
			@RequestParam(value = "message",required = false) String message,
			Model model
			) {
		model.addAttribute("message",message);
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
			RedirectAttributes attributes
			) {
		String message=null;
		try {
			service.removeDoctor(id);
			message="Doctor removed";
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

	/**
	 * 6. Do update
	 */
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
