package com.ocsp.backend.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocsp.backend.project.dao.CustomerRepository;
import com.ocsp.backend.project.exception.CustomerCareException;
import com.ocsp.backend.project.model.Customer;


@Service
public class CustomerService {

	@Autowired
	CustomerRepository userRepository;

	int profileId;

	public int loginId(int id) {
		profileId = id;
		return id;
	}

	public void save(Customer user) {
		userRepository.save(user);
	}

	public int viewId() {
		return profileId;
	}

	public List<Customer> completeList() {
		List<Customer> list = userRepository.findAll();
		return list;
	}

	public String login(Customer user) {
		String pageName = "";
		List<Customer> lst = completeList();
		if ((user.getFirstName() == null) || (user.getPassword() == null)) {
			return pageName;
		} else {
			for (Customer u : lst) {
				if (u.getFirstName().equals(user.getFirstName()) && u.getPassword().equals(user.getPassword())) {
					if (u.getUserType().equalsIgnoreCase("User")) {
						loginId(u.getUserId());
						pageName = "redirect:/complaintRegister";
					} else if (u.getUserType().equalsIgnoreCase("Support Analyst")) {
						loginId(u.getUserId());
						pageName = "redirect:/operatorHome";
					} else if (u.getUserType().equalsIgnoreCase("Admin")) {
						loginId(u.getUserId());
						pageName = "redirect:/adminhome";
					}
				}
			}
			return pageName;
		}
	}

	public List<String> getAllUserNames() {
		List<String> userNames = new ArrayList<>();
		List<Customer> user = completeList();
		for (Customer user1 : user) {
			userNames.add(user1.getFirstName());
		}
		return userNames;
	}

	public List<String> getAllPasswords() {
		List<String> passwords = new ArrayList<>();
		List<Customer> user = completeList();
		for (Customer user1 : user) {
			passwords.add(user1.getPassword());
		}
		return passwords;
	}

	public void saveUser(Customer user) {

		userRepository.save(user);
	}

	public Customer findUserById(int id) throws CustomerCareException {

		Optional<Customer> optional = userRepository.findById(id);
		Customer category = null;
		if (optional.isPresent()) {
			category = optional.get();
		} else

		{
			throw new CustomerCareException("Not Found For User Id: " + id);
		}
		return category;
	}

	public String forgotId(Customer user) {
		List<Customer> list = completeList();
		String name = "Your User Name for Login '";
		for (Customer u : list) {
			if (u.getEmail().equals(user.getEmail())) {
				if (u.getAnswer().equals(user.getAnswer()) && u.getSecretQuestion().equals(user.getSecretQuestion())) {
					name = name + u.getFirstName() + "'";
					return name;
				}
			}
		}
		return "User Not Found";
	}

	int userId;

	public boolean resetPassword(Customer user) {
		List<Customer> list = completeList();
		for (Customer u : list) {
			if (user.getFirstName().equals(u.getFirstName())) {
				if (user.getEmail().equals(u.getEmail()) && user.getContactNumber().equals(u.getContactNumber())
						&& user.getSecretQuestion().equals(u.getSecretQuestion())
						&& user.getAnswer().equals(u.getAnswer())) {
					userId = u.getUserId();
					return true;
				}
			}
		}
		return false;
	}

	public void password(String newPassword) {
		List<Customer> list = completeList();
		for (Customer u : list) {
			if (userId == u.getUserId()) {
				u.setPassword(newPassword);
				userRepository.save(u);
			}
		}

	}
}