package com.ocsp.backend.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Complaint {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int complaintId;
	private String date;
	private int userId;
	private String categoryName;
	private String complaintStatement;
	private String levelOfIssue;
	private String status;

	public Complaint() {
		super();
	}

	public Complaint(int complaintId, String date, int userId, String categoryName, String complaintStatement,
			String levelOfIssue, String status) {
		super();
		this.complaintId = complaintId;
		this.date = date;
		this.userId = userId;
		this.categoryName = categoryName;
		this.complaintStatement = complaintStatement;
		this.levelOfIssue = levelOfIssue;
		this.status = status;
	}

	public int getComplaintId() {
		return complaintId;
	}

	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getComplaintStatement() {
		return complaintStatement;
	}

	public void setComplaintStatement(String complaintStatement) {
		this.complaintStatement = complaintStatement;
	}

	public String getLevelOfIssue() {
		return levelOfIssue;
	}

	public void setLevelOfIssue(String levelOfIssue) {
		this.levelOfIssue = levelOfIssue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Complaint [complaintId=" + complaintId + ", date=" + date + ", userId=" + userId + ", categoryName="
				+ categoryName + ", complaintStatement=" + complaintStatement + ", levelOfIssue=" + levelOfIssue
				+ ", status=" + status + "]";
	}

	

}