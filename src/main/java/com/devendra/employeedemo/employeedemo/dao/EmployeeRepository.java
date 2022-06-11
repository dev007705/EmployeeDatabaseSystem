package com.devendra.employeedemo.employeedemo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.ResponseEntity;

import com.devendra.employeedemo.employeedemo.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee,String>{

	List<Employee> findByName(String name);
	
	
	
}
