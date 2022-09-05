package com.ocsp.backend.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ocsp.backend.project.model.Complaint;
import com.ocsp.backend.project.model.Operator;



@Repository
public interface OperatorRepository extends JpaRepository<Complaint, Integer> {

	void save(Operator user);

}