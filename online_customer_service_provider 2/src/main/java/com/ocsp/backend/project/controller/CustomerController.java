package com.ocsp.backend.project.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.ocsp.backend.project.dao.CustomerRepository;
import com.ocsp.backend.project.model.Complaint;
import com.ocsp.backend.project.model.Customer;
import com.ocsp.backend.project.service.AdminService;
import com.ocsp.backend.project.service.CustomerService;
import com.ocsp.backend.project.service.OperatorService;

@Controller
public class CustomerController {

	@Autowired
	CustomerRepository userRepository;

	@Autowired
	CustomerService userService;

	@Autowired
	OperatorService complaintService;

	@Autowired
	AdminService categoryService;

	Customer user = new Customer();

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String Login(@ModelAttribute("Login") Customer user) {
		return "login";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signIn(@ModelAttribute("Login") Customer user, ModelMap model) {
		String pageName = userService.login(user);
		if (pageName.equals("")) {
			if (!(userService.getAllPasswords().contains(user.getPassword()))
					&& (!(userService.getAllUserNames().contains(user.getFirstName()))) || (user.getFirstName() == null)
					|| (user.getPassword() == null)) {
				model.put("msg", "Invalid Username or Invalid Password");
			} else if (!(userService.getAllUserNames().contains(user.getFirstName()))) {
				model.put("userNameMsg", "Invalid Username");
			} else if ((!(userService.getAllPasswords().contains(user.getPassword())))) {
				model.put("passwordMsg", "Invalid Password");
			}

			return "login";
		} else {
			return pageName;
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@ModelAttribute("register") Customer user) {
		return "Register";
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public String registered( @ModelAttribute("register") Customer user, ModelMap map, BindingResult result) {
		if (result.hasErrors()) {
			return "Register";
		}
		userService.saveUser(user);
		map.put("Role", user.getUserType());
		return "Registersuccess";
	}

	@RequestMapping(value = "/forgotId", method = RequestMethod.GET)
	public String forgotId(@ModelAttribute("command") Customer user) {
		return "forgotId";
	}

	@RequestMapping(value = "/userId", method = RequestMethod.GET)
	public String UserId(@ModelAttribute("command") Customer user, ModelMap map) {
		String userName = userService.forgotId(user);
		map.put("userId", userName);
		return "userId";
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET)
	public String forgotpassword(@ModelAttribute("command") Customer user) {
		return "forgotpassword";
	}

	@RequestMapping(value = "/resetpassword", method = RequestMethod.GET)
	public String resetpassword(@ModelAttribute("command") Customer user) {
		boolean reset = userService.resetPassword(user);
		if (reset) {
			return "resetpassword";
		}
		return "forgotpassword";
	}

	@RequestMapping(value = "/resetedSucess", method = RequestMethod.GET)
	public String resetedPassword(@ModelAttribute("command") Customer user) {

		userService.password(user.getPassword());

		return "resetSucess";
	}

	@RequestMapping("/adminhome")
	public String goToHome(@ModelAttribute("complaint") Complaint complaint, ModelMap model) {

		List<Complaint> lst = complaintService.findallcomplaints();
		List<Complaint> lst1 = new ArrayList<>();
		for (Complaint complaint1 : lst) {
			if (((complaint1.getStatus()) == null) || (complaint1.getStatus().equals("re-open"))) {
				lst1.add(complaint1);
			}
		}

		model.put("complaintList", lst1);
		return "adminhome";
	}

	@RequestMapping("/operatorHome")
	public String operatorHome(@ModelAttribute("complaint") Complaint complaint, ModelMap model) throws Exception {
		int analystId = userService.viewId();
		@SuppressWarnings("unused")
		Customer user1 = userService.findUserById(analystId);

		List<Complaint> analystComplaints = new ArrayList<>();
		List<Complaint> lst = complaintService.findallcomplaints();
		for (Complaint complaint1 : lst) {
			if ((complaint1.getStatus() != null)) {
				if (!((complaint1.getStatus()).equals("closed"))) {
						analystComplaints.add(complaint1);
					}
				}
		
		}

		model.put("complaintList", analystComplaints);
		return "operatorHome";

	}

	@RequestMapping("/complaintRegister")
	public String complaintRegister(@ModelAttribute("command") Complaint complaint, ModelMap model) throws Exception {
		int id = userService.viewId();

		Customer user1 = userService.findUserById(id);
		model.put("userId", id);
		model.put("userName", user1.getFirstName());
		model.put("contactNumber", user1.getContactNumber());

		return "complaintRegister";
	}

	@RequestMapping(value = "/complaintsuccess", method = RequestMethod.POST)
	public String success(@ModelAttribute("command") Complaint complaint, ModelMap model) {
		int id = userService.viewId();

		complaintService.saveComplaint(complaint);
		model.put("userId", id);
		return "complaintsuccess";
	}

	@RequestMapping("/reopen/{id}")
	public String reopen(@PathVariable(value = "id") int id) throws Exception {
		Complaint complaint = complaintService.findComplaintById(id);
		complaint.setStatus("re-open");
		
		complaintService.saveComplaint(complaint);
		return "redirect:/complaintRegister";
	}
	
	@RequestMapping("/remove/{id}")
	public String close(@PathVariable(value = "id") int id) throws Exception {
		
		complaintService.deleteComplaint(id);
		
		
		return "redirect:/complaintRegister";
	}
	

	@RequestMapping("/searchComplaints")
	public String searchComplaints(ModelMap model) {
		int id = userService.viewId();
		List<Complaint> lst = complaintService.findallcomplaints();
		List<Complaint> lst1 = new ArrayList<>();
		for (Complaint complaint1 : lst) {

			lst1.add(complaint1);

		}

		model.put("userId", id);

		model.put("complaintList", lst1);
		return "searchComplaint";
	}

	@RequestMapping(value = "/profilepage", method = RequestMethod.GET)
	public String ProfilePage(@ModelAttribute("profile") Customer user, ModelMap map) throws Exception {
		int id = userService.viewId();
		user = userService.findUserById(id);
		map.put("userName", user.getFirstName() + ' ' + user.getLastName());
		map.put("userType", user.getUserType());
		map.put("userId", user.getUserId());
		map.put("firstName", user.getFirstName());
		map.put("gender", user.getGender());
		map.put("contactNumber", user.getContactNumber());
		map.put("dob", user.getDob());
		return "Profile";
	}

	@RequestMapping(value = "/editprofile/{id}", method = RequestMethod.GET)
	public String editProfilePage(@PathVariable(value = "id") int id, @ModelAttribute("profile") Customer user,
			ModelMap model) throws Exception {

		Customer user1 = userService.findUserById(id);
		model.put("user", user1);
		return "editprofile";
	}

	@RequestMapping(value = "/changeProfile/{id}", method = RequestMethod.POST)
	public String editedProfilePage(@PathVariable(value = "id") int id, @ModelAttribute("profile") Customer user,
			ModelMap map) throws Exception {

		Customer user1 = userService.findUserById(id);

		user1.setContactNumber(user.getContactNumber());
		user1.setFirstName(user.getFirstName());
		user1.setLastName(user.getLastName());
		user1.setGender(user.getGender());
		user1.setDob(user.getDob());
		userService.saveUser(user1);

		return "redirect:/profilepage";
	}

	@RequestMapping("/userComplaintStatus/{id}")
	public String userComplaintStatus(@PathVariable(value = "id") int userId, ModelMap model) {
		int id = userService.viewId();
		List<Complaint> lst = complaintService.findallcomplaints();
		List<Complaint> lst1 = new ArrayList<>();
		for (Complaint complaint : lst) {
			if (complaint.getUserId() == userId) {
				if ((complaint.getStatus()) != null) {

					lst1.add(complaint);

				}
			}
		}

		model.put("userId", id);
		model.put("complaintList", lst1);
		return "userComplaintStatus";
	}

	@RequestMapping("/returnToHome/{id}")
	public String returnToHome(@PathVariable(value = "id") int id) throws Exception {

		Customer user = userService.findUserById(id);
		String pageName = null;
		if (user.getUserType().equals("User")) {
			pageName = "redirect:/complaintRegister";
		}
		if (user.getUserType().equals("Admin")) {
			pageName = "redirect:/adminhome";

		}
		if (user.getUserType().equals("Support Analyst")) {
			pageName = "redirect:/analystHome";

		}
		return pageName;
	}


	@RequestMapping("/userlogin")
	public String logout() {
		return "redirect:/login";
	}

	@ModelAttribute("roles")
	public List<String> roleSelection() {
		List<String> types = new ArrayList<String>();
		types.add("Role Selection");
		types.add("Admin");
		types.add("User");
		types.add("Support Analyst");
		return types;
	}


	@ModelAttribute("gender")
	public List<String> Gender() {
		List<String> types = new ArrayList<String>();
		types.add("Gender");
		types.add("Male");
		types.add("Female");
		return types;
	}


	@ModelAttribute("statusList")
	public List<String> statusList() {

		List<String> statusList = new ArrayList<>();

		statusList.add("can be open");
		statusList.add("work in progress");
		statusList.add("closed");
		statusList.add("re-open");
		return statusList;
	}

	@ModelAttribute("secretQuestion")
	public List<String> Question1() {
		List<String> types = new ArrayList<String>();
		types.add("Secret Questions");
		types.add("Which city were you born in?");
		types.add("What is your dream car?");
		types.add("What is your favorite food?");
		return types;
	}

}