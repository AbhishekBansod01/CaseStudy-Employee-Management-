package com.employee.version.v1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.version.v1.request.EmployeeRequest;
import com.employee.version.v1.response.EmployeeResponse;
import com.employee.version.v1.service.EmployeeService;

import jakarta.validation.Valid;

/**
 * This RestController class is used to create ,update, get, delete employee
 * data
 * 
 * @author abbansod
 */

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * This Mapping method is used to get data of employee from user using
	 * employeeRequest class and add that data into the database
	 * 
	 * @param employeeRequest @EmployeeRequest class to accept the data from the
	 *                        user
	 * @return @ResponseEntity<EmployeeResponse> response class of EmployeeRequest
	 */
	@PostMapping(value = "/")
	public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest employeeRequest) {

		return ResponseEntity.of(Optional.of(employeeService.createEmployee(employeeRequest)));

	}

	@GetMapping("/")
	public ResponseEntity<List<EmployeeResponse>> getAllEmployees() throws Exception {

		return ResponseEntity.of(Optional.of(employeeService.getAllEmployees()));

	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable("id") String empId) {

		return ResponseEntity.of(Optional.of(employeeService.getEmployeeById(empId)));
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponse> updateEmployeeById(@PathVariable("id") String empId,
			@RequestBody EmployeeRequest employeeRequest) {

		return ResponseEntity.of(Optional.of(employeeService.updateEmployeeById(employeeRequest, empId)));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteEmployeeById(@PathVariable("id") String empId,
			@RequestParam("confirmDelete") String confirmDelete) {

		switch (confirmDelete) {
		case "Y":
			return ResponseEntity.ok(employeeService.deleteEmployeeById(empId));
		case "N":
			return ResponseEntity.ok("Soft Delete Succefully");

		default:
			return ResponseEntity
					.ok("Bad Request please check the value of confirmDelete it can be \"Y\" or \"N\" only");
		}

	}

	@DeleteMapping("/")
	public ResponseEntity<Object> deleteAllEmployees() {
		String status = employeeService.deleteAllEmployees();
		return ResponseEntity.ok(status);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<EmployeeResponse> updateEmployeeDataById(@PathVariable("id") String empId,
			@Valid @RequestBody EmployeeRequest employeeRequest) {
		return ResponseEntity.of(Optional.of(employeeService.updateEmployeeDataById(employeeRequest, empId)));

	}

}
