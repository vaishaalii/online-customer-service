package com.ocsp.backend.project.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ocsp.backend.project.model.Review;


@SpringBootTest
public class ReviewServiceTest {
	
	@Autowired
	ReviewService reviewService;
	
	@Before
	public void setUp() throws Exception{	
		reviewService = new ReviewService();	
	}
	
	@Test
	public void testValidReviewList() {
		Review review = new Review();
		review.setQuestionId(18);
		review.setQuestion("Any suggestions to improve");
		review.setFeedback("Not needed");
		reviewService.saveReview(review);
		List<Review> lst = reviewService.findallreviews();
		@SuppressWarnings("unused")
		Review review1=lst.stream().filter(c->c.getQuestion().equals("Any suggestions to improve")).findFirst().get();
	}
	@Test
	public void testInValidReviewList() {
		
	}
}