package com.ocsp.backend.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ocsp.backend.project.exception.CustomerCareException;
import com.ocsp.backend.project.model.Department;
import com.ocsp.backend.project.service.AdminService;



@Controller
public class AdminController {

	@Autowired
	AdminService departmentService;

	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public String showCategories(ModelMap model) {

		
		model.put("DepartmentList", departmentService.findAllCategories());
		return "Departmentlist";
	}

	@RequestMapping(value = "/updateDepartment/{id}")
	public String updateCategories(@PathVariable(value = "id") int id, @ModelAttribute("command") Department Department,
			ModelMap model) throws CustomerCareException  {

		Department Department1 = departmentService.findDepartmentById(id);
		model.put("Department", Department1);
		return "updateDepartment";

	}

	@RequestMapping(value = "/editDepartment/{id}", method = RequestMethod.POST)
	public String updateDepartment(@PathVariable(value = "id") int id, @ModelAttribute("Department") Department Department,
			ModelMap model) throws CustomerCareException  {

		Department Department2 = departmentService.findDepartmentById(id);
		Department2.setDepartmentName(Department.getDepartmentName());
		Department2.setDescription(Department.getDescription());
		departmentService.saveDepartment(Department2);
		return "redirect:/categories";
	}

	@RequestMapping(value = "/addDepartment")
	public String addDepartmentForm(@ModelAttribute("command") Department Department) {

		return "addDepartment";
	}

	@RequestMapping(value = "/saveDepartment", method = RequestMethod.POST)
	public String saveDepartment(@ModelAttribute("Department") Department Department) {

		departmentService.saveDepartment(Department);
		return "redirect:/categories";
	}

	@RequestMapping("/deleteDepartment/{id}")
	public String deleteCategories(@PathVariable(value = "id") int id) {

		departmentService.deleteDepartment(id);
		return "redirect:/categories";
	}

}
