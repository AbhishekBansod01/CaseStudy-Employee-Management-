package com.employee.version.v1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.version.v1.request.EmployeeRequest;
import com.employee.version.v1.response.EmployeeResponse;

@Service
public interface EmployeeService {

	public EmployeeResponse createEmployee(EmployeeRequest employeeRequest);

	public List<EmployeeResponse> getAllEmployees();

	public EmployeeResponse getEmployeeById(String empId);

	public EmployeeResponse updateEmployeeById(EmployeeRequest employeeRequest, String empId);

	public String deleteEmployeeById(String empId);

	public String deleteAllEmployees();

	public EmployeeResponse updateEmployeeDataById( EmployeeRequest employeeRequest, String empId);

}
