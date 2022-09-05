package com.ocsp.backend.project.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.ocsp.backend.project.model.Complaint;



@SpringBootTest
public class ComplaintServiceTest {
	@Autowired
	OperatorService complaintService;

	@Before
	public void setUp() throws Exception {
		complaintService=new OperatorService();
	}

	@Test
	public void testValidComplaintList() {
		Complaint complaint=new Complaint();
		complaint.setUserId(1);
		
		complaint.setComplaintStatement("complaint");
		complaintService.saveComplaint(complaint);
		List<Complaint> lst=complaintService.findallcomplaints();
		
		Complaint complaint1=lst.stream().filter(c->c.getComplaintStatement().equals("complaint")).findFirst().get();
	}
	
	@Test
	public void testInValidComplaintList() {
	}


}