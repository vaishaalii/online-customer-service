package com.ocsp.backend.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int questionId;
	private String question;
	private String feedback;
	private int userId;
	
	public Review(int questionId, String question, String feedback, int userId) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.feedback = feedback;
		this.userId = userId;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Review() {
		super();
	}
	@Override
	public String toString() {
		return "Review [questionId=" + questionId + ", question=" + question + ", feedback=" + feedback + ", userId="
				+ userId + "]";
	}
	
	
}