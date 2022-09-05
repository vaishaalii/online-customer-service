package com.ocsp.backend.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String firstName;
	private String lastName;
	private String password;
	private String dob;
	private String gender;
	private String contactNumber;
	private String userType;
	private String email;
	private String secretQuestion;
	private String answer;
	private String assignFeedback;
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Customer(int userId, String firstName, String lastName, String password, String dob, String gender,
			String contactNumber, String userType, String email, String secretQuestion, String answer,
			String assignFeedback) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.dob = dob;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.userType = userType;
		this.email = email;
		this.secretQuestion = secretQuestion;
		this.answer = answer;
		this.assignFeedback = assignFeedback;
	}
	@Override
	public String toString() {
		return "Customer [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", dob=" + dob + ", gender=" + gender + ", contactNumber=" + contactNumber + ", userType="
				+ userType + ", email=" + email + ", secretQuestion=" + secretQuestion + ", answer=" + answer
				+ ", assignFeedback=" + assignFeedback + "]";
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSecretQuestion() {
		return secretQuestion;
	}
	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getAssignFeedback() {
		return assignFeedback;
	}
	public void setAssignFeedback(String assignFeedback) {
		this.assignFeedback = assignFeedback;
	}
	
	

}
