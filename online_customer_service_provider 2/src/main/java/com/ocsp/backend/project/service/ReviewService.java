package com.ocsp.backend.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocsp.backend.project.dao.ReviewRepository;
import com.ocsp.backend.project.model.Review;


@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	public List<Review> findallreviews() {

		return reviewRepository.findAll();
	}
	public void saveReview(Review review) {

		reviewRepository.save(review);
	}
	
	public void deleteReview(int id) {

		reviewRepository.deleteById(id);
	}
	
	public Review findReviewById(int id) throws Exception {

		Optional<Review> optional = reviewRepository.findById(id);
		Review review = null;
		if (optional.isPresent()) {
			review = optional.get();
		} else {
			throw new Exception("Not Found For Review Id: " + id);
		}
		return review;
	}
	
}