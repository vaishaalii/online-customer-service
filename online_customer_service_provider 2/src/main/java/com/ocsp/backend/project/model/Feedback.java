package com.ocsp.backend.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Feedback {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int userId;
	private String question;
	private String feedback;
	
	
	
	public Feedback(int id, int userId, String question, String feedback) {
		super();
		this.id = id;
		this.userId = userId;
		this.question = question;
		this.feedback = feedback;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Feedback() {
		super();
	}
	
	

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}



	@Override
	public String toString() {
		return "Feedback [id=" + id + ", userId=" + userId + ", question=" + question + ", feedback=" + feedback + "]";
	}

	
	
	
	
	
}