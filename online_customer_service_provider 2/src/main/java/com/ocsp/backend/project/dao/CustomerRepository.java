package com.ocsp.backend.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ocsp.backend.project.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{

}