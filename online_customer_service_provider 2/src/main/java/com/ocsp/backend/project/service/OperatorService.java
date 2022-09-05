package com.ocsp.backend.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocsp.backend.project.dao.OperatorRepository;
import com.ocsp.backend.project.exception.CustomerCareException;
import com.ocsp.backend.project.model.Complaint;


@Service
public class OperatorService {

	@Autowired
	private OperatorRepository complaintRepository;
	@Autowired
	CustomerService userService;

	public List<Complaint> findallcomplaints() {

		return complaintRepository.findAll();
	}
	
	public void assignTeam(Complaint complaint, int id) throws CustomerCareException  {
		Complaint complaint1 = findComplaintById(id);
		complaint1.setStatus(complaint.getStatus());
		complaint1.setCategoryName(complaint.getCategoryName());

		saveComplaint(complaint1);
	}

	public Complaint findComplaintById(int id) throws CustomerCareException {

		Optional<Complaint> optional = complaintRepository.findById(id);
		Complaint complaint = null;
		if (optional.isPresent()) {
			complaint = optional.get();
		} else {
			throw new CustomerCareException ("Not Found For Complaint Id: " + id);
		}
		return complaint;
	}

	public void saveComplaint(Complaint complaint) {

		complaintRepository.save(complaint);
	}

	public void deleteComplaint(int id) {

		complaintRepository.deleteById(id);
	}
	
	public List<Complaint> getComplaintStatus(){
	List<Complaint> lst = findallcomplaints();
	List<Complaint> lst1 = new ArrayList<>();
		for (Complaint complaint1 : lst) {
			if ((complaint1.getStatus()) != null) {
				if (!((complaint1.getStatus()).equals("closed"))) {
					lst1.add(complaint1);
				}
			}
		}
		return lst1;

	}
	
	public void saveComplaintStatus(Complaint complaint, int id) throws CustomerCareException {
		Complaint complaint1 = findComplaintById(id);
		complaint1.setStatus(complaint.getStatus());
		saveComplaint(complaint1);
	}
	
	
	
	
}