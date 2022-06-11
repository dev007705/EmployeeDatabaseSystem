package com.devendra.employeedemo.employeedemo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devendra.employeedemo.employeedemo.dao.EmployeeRepository;
import com.devendra.employeedemo.employeedemo.exception.ResourceNotFoundException;
import com.devendra.employeedemo.employeedemo.model.Employee;


@SpringBootApplication
@Configuration
@RestController
@RequestMapping("/api/v1")
public class EmployeedemoApplication {

	@Autowired
	private EmployeeRepository repo;

	@PostMapping("/employees")
	@PreAuthorize("hasRole('HR_ADMIN') or hasRole('ADMIN')")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return repo.save(employee);
	}
	
	@GetMapping("/employees")
	@PreAuthorize("hasRole('HR') or hasRole('HR_ADMIN') or hasRole('ADMIN')")
	public List<Employee> getEmployee(){
		return getPagginated(0);
	}
	
	@GetMapping("/employees/id/{id}")
	@PreAuthorize("hasRole('HR') or hasRole('HR_ADMIN') or hasRole('ADMIN')")
	public ResponseEntity<Employee> getEmployeeByID(@PathVariable String id){
		Employee employee = repo.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

		    return new ResponseEntity<>(employee, HttpStatus.OK);
		
	}
	
	@GetMapping("/employees/name/{name}")
	@PreAuthorize("hasRole('HR') or hasRole('HR_ADMIN') or hasRole('ADMIN')")
	public List<Employee> getEmployeeByName(@PathVariable String name){
		return repo.findByName(name);
	}
	
	@GetMapping("/employees/{pageNo}")
	@PreAuthorize("hasRole('HR') or hasRole('HR_ADMIN') or hasRole('ADMIN')")
	public List<Employee> getPagginated(@PathVariable int pageNo){
		Pageable paging=PageRequest.of(pageNo, 5);
		Page<Employee> result=repo.findAll(paging);
		return result.toList();
		
	}
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String DeleteEmpoyeeByID(@PathVariable String id){
		if(repo.findById(id).isPresent()) {
		     repo.deleteById(id);
		     return "Employee data have been Deleted";
		}
		else{
		     return "Employee Not found";
		}
	}
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('HR_ADMIN') or hasRole('ADMIN')")
	public String UpdateEmpoyeeByID(@RequestBody Employee employee,@PathVariable String id){
		if(repo.findById(id).isPresent()) {
			repo.save(employee);
			return "Data have been Updated Succesfully";
		}
		return "Id Not found";
	}
	
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeedemoApplication.class, args);
	}

}

