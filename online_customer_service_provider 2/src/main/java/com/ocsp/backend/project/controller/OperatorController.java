package com.ocsp.backend.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ocsp.backend.project.exception.CustomerCareException;
import com.ocsp.backend.project.model.Complaint;
import com.ocsp.backend.project.model.Customer;
import com.ocsp.backend.project.service.AdminService;
import com.ocsp.backend.project.service.CustomerService;
import com.ocsp.backend.project.service.OperatorService;



@Controller
public class OperatorController {

	@Autowired
	AdminService categoryService;

	@Autowired
	OperatorService complaintService;

	@Autowired
	CustomerService userService;

	@ModelAttribute("statusList")
	public List<String> statusList() {

		List<String> statusList = new ArrayList<>();
		statusList.add("Select Status");
		statusList.add("can be open");
		statusList.add("work in progress");
		statusList.add("closed");
		statusList.add("reopen");
		return statusList;
	}



	@RequestMapping("/viewComplaint/{id}")
	public String complaintAssign(@PathVariable(value = "id") int id, ModelMap model) throws CustomerCareException {

		Complaint complaint = complaintService.findComplaintById(id);
		int userId = complaint.getUserId();
		Customer user = userService.findUserById(userId);

		model.put("user", user);
		model.put("complaint", complaint);
		return "viewComplaint";
	}

	@RequestMapping("/complaintStatus")
	public String complaintStatus(@ModelAttribute("complaint") Complaint complaint, ModelMap model) {
		
		List<Complaint> lst1 = new ArrayList<>();
		lst1 = complaintService.getComplaintStatus();
		
		model.put("complaintList", lst1);
		return "complaintStatus";
	}

	@RequestMapping("/saveComplaintStatus/{id}")
	public String saveComplaintStatus(@PathVariable(value = "id") int id,
			@ModelAttribute("complaint") Complaint complaint, ModelMap model) throws CustomerCareException {
		
		complaintService.saveComplaintStatus(complaint, id);
		return "redirect:/complaintStatus";

	}

	@RequestMapping("/viewComplaintStatus/{id}")
	public String changeStatusForm(@PathVariable(value = "id") int id, @ModelAttribute("complaint") Complaint complaint,
			ModelMap model) throws CustomerCareException {

		Complaint complaint1 = complaintService.findComplaintById(id);
		int userId = complaint1.getUserId();
		Customer user = userService.findUserById(userId);

		model.put("user", user);
		model.put("complaint", complaint1);
		return "viewComplaintStatus";
	}

	

	
	@RequestMapping("/viewComplaintForOperator/{id}")
	public String viewComplaintForAnalyst(@PathVariable(value = "id") int id, ModelMap model) throws CustomerCareException {

		Complaint complaint = complaintService.findComplaintById(id);
		int userId = complaint.getUserId();
		Customer user = userService.findUserById(userId);
		
		model.put("user", user);
		model.put("complaint", complaint);
		return "viewComplaintForAnalyst";
	}

}