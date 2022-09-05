package com.ocsp.backend.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocsp.backend.project.dao.AdminRepository;
import com.ocsp.backend.project.exception.CustomerCareException;
import com.ocsp.backend.project.model.Department;


@Service
public class AdminService {

	@Autowired
	private AdminRepository DepartmentRepository;

	public List<Department> findAllCategories() {

		return DepartmentRepository.findAll();
	}

	public Department findDepartmentById(int id) throws CustomerCareException  {

		Optional<Department> optional = DepartmentRepository.findById(id);
		Department Department = null;
		if (optional.isPresent()) {
			Department = optional.get();
		} else

		{
			throw new CustomerCareException("Not Found For Department Id: " + id);
		}
		return Department;
	}

	public void saveDepartment(Department Department) {

		DepartmentRepository.save(Department);
	}

	public void deleteDepartment(int id) {

		DepartmentRepository.deleteById(id);
	}

}
