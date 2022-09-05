package com.ocsp.backend.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ocsp.backend.project.dao.FeedbackRepository;
import com.ocsp.backend.project.model.Complaint;
import com.ocsp.backend.project.model.Customer;
import com.ocsp.backend.project.model.Feedback;
import com.ocsp.backend.project.model.Review;
import com.ocsp.backend.project.service.CustomerService;
import com.ocsp.backend.project.service.OperatorService;
import com.ocsp.backend.project.service.ReviewService;



@Controller
public class ReviewController {

	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	ReviewService reviewService;

	@Autowired
	OperatorService complaintService;

	@Autowired
	CustomerService userService;

	@RequestMapping(value = "/reviewList", method = RequestMethod.GET)
	public String showCategories(ModelMap model) {

		model.put("reviewlist", reviewService.findallreviews());
		return "reviewList";
	}

	@RequestMapping(value = "/savereview", method = RequestMethod.POST)
	public String success(@ModelAttribute("command") Review review) {

		reviewService.saveReview(review);
		return "redirect:/reviewList";
	}

	@RequestMapping(value = "/addReview")
	public String addReview(@ModelAttribute("command") Review review) {
		return "addReview";
	}

	@RequestMapping("/deleteReview/{id}")
	public String deleteReview(@PathVariable(value = "id") int id) {

		reviewService.deleteReview(id);
		return "redirect:/reviewList";
	}

	@RequestMapping("/feedback")
	public String userFeedback(ModelMap model) {
		
		List <Feedback> lst = feedbackRepository.findAll();
		
		model.put("review",lst);
		
		return "Feedback";
	}

	@RequestMapping("/askFeedback")
	public String complaintStatus(ModelMap model) throws Exception {

		List<Complaint> lst = complaintService.findallcomplaints();
		List<Complaint> lst1 = new ArrayList<>();

		for (Complaint complaint : lst) {

			if ((complaint.getStatus()) != null) {

				if (complaint.getStatus().equals("closed")) {
					lst1.add(complaint);
				}
			}
		}

		model.put("complaintList", lst1);
		return "askFeedback";
	}

	@RequestMapping("/saveaskfeedback/{id}")
	public String assignfeedback(@PathVariable(value = "id") int id) throws Exception {
		Customer user = userService.findUserById(id);
		user.setAssignFeedback("yes");
		System.out.println(user.getAssignFeedback());
		userService.saveUser(user);
		return "redirect:/askFeedback";
	}

	@RequestMapping(value="/userFeedback/{id}")
	public String userFeedback(@PathVariable(value = "id") int id, @ModelAttribute("review") Review review, ModelMap model) {
		
		List<Review> lst = reviewService.findallreviews();
		
		for (Review rev : lst) {
			rev.setUserId(id);
			reviewService.saveReview(rev);
		}
		
		model.put("userId",id );
		model.put("reviewlist", reviewService.findallreviews());
		return "userFeedback";
	}
	@RequestMapping(value="/userfeed/{id}", method = RequestMethod.POST)
	public String userFeed(@PathVariable(value = "id") int id, @ModelAttribute("review") Review review, ModelMap model) throws Exception {
		
		int userid=id;
		List<Review> rev = reviewService.findallreviews();
		String str[]=new String[10];
		str=review.getFeedback().split(",");
		int j=0;
		for(Review r:rev) {
			if(r.getUserId()==userid) {
				r.setFeedback(str[j]);
				reviewService.saveReview(r);
			}
			
			j++;
		}
		
		
		List<Review> lst1 = reviewService.findallreviews();
		Feedback feed = new Feedback();
		
		String ques=" ",feedb=" ";
		for(Review rev1:lst1) {
			
			feed.setUserId(rev1.getUserId());
			
			if(rev1.getQuestionId()!=1) {
			ques=ques+","+rev1.getQuestion();
			feedb=feedb+","+rev1.getFeedback();
			}
			else {
				ques=ques+rev1.getQuestion();
				feedb=feedb+rev1.getFeedback();
			}
		}
		
		feed.setQuestion(ques);
		feed.setFeedback(feedb);
		feedbackRepository.save(feed);
		return "redirect:/complaintRegister";
	}
}