package com.employee.version.v1.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.employee.version.v1.entities.EmployeeEntity;
import com.employee.version.v1.request.EmployeeRequest;
import com.employee.version.v1.response.EmployeeResponse;
import com.employee.version.v1.respository.EmployeeRepository;
import com.employee.version.v1.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@Value("${default.value.role:Ab}")
	private String role;

	@Override
	public EmployeeResponse createEmployee(EmployeeRequest employeeRequest) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		employeeEntity = employeeEntity.toBuilder().empId(employeeRequest.getEmpId())
				.empName(employeeRequest.getEmpName()).gender(employeeRequest.getGender())
				.role(employeeRequest.getRole()).salary(employeeRequest.getSalary()).build();

		EmployeeEntity empEntity = employeeRepository.save(employeeEntity);

		return EmployeeResponse.builder().empId(empEntity.getEmpId()).empName(empEntity.getEmpName())
				.gender(empEntity.getGender()).role(empEntity.getRole()).salary(empEntity.getSalary()).build();

	}

	@Override
	public List<EmployeeResponse> getAllEmployees() {

		List<EmployeeEntity> employees = employeeRepository.findAll();
		List<EmployeeResponse> employeeResponses = new ArrayList<>();

		employees.stream().forEach(employee ->

		employeeResponses.add(EmployeeResponse.builder().empId(employee.getEmpId()).empName(employee.getEmpName())
				.gender(employee.getGender()).role(employee.getRole()).salary(employee.getSalary()).build()));

		return employeeResponses;
	}

	@Override
	public EmployeeResponse getEmployeeById(String empId) {

		EmployeeEntity employeeEntity = employeeRepository.findEmployeeByEmpId(empId)
				.orElse(EmployeeEntity.builder().build());

		return EmployeeResponse.builder().empId(employeeEntity.getEmpId()).empName(employeeEntity.getEmpName())
				.gender(employeeEntity.getGender()).role(employeeEntity.getRole()).salary(employeeEntity.getSalary())
				.build();

	}

	@Override
	public EmployeeResponse updateEmployeeById(EmployeeRequest employeeRequest, String empId) {
		Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findEmployeeByEmpId(empId);

		if (employeeEntityOptional.isPresent()) {

			EmployeeEntity employeeEntity = employeeRepository
					.save(EmployeeEntity.builder().empId(employeeRequest.getEmpId())
							.empName(employeeRequest.getEmpName()).gender(employeeRequest.getGender())
							.role(employeeRequest.getRole()).salary(employeeRequest.getSalary()).build());

			return EmployeeResponse.builder().empId(employeeEntity.getEmpId()).empName(employeeEntity.getEmpName())
					.gender(employeeEntity.getGender()).role(employeeEntity.getRole())
					.salary(employeeEntity.getSalary()).build();
		}

		return EmployeeResponse.builder().build();
	}

	@Override
	public String deleteEmployeeById(String empId) {
		Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findEmployeeByEmpId(empId);

		if (employeeEntityOptional.isEmpty())
			return "Employee Doesn't Exist";

		employeeRepository.deleteById(empId);
		return "Employee : " + employeeEntityOptional.get().getEmpName() + "  Successfully Deleted";

	}

	@Override
	public String deleteAllEmployees() {
		List<EmployeeEntity> employeeEntities = employeeRepository.findAll();

		employeeRepository.deleteAll();
		List<String> emplos = employeeEntities.stream().flatMap(e -> Stream.of(e.getEmpName())).toList();
		if(!CollectionUtils.isEmpty(emplos))
			return "Employees : " + emplos + " deleted Successfully";
		else
			return "No employee data present to be deleted";

	}

	@Override
	public EmployeeResponse updateEmployeeDataById(EmployeeRequest employeeRequest, String empId) {
		Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findEmployeeByEmpId(empId);

		if (employeeEntityOptional.isEmpty()) {
			System.out.println("Employee not present");
			return EmployeeResponse.builder().build();
		}

		else {
			EmployeeEntity employeeEntity = employeeEntityOptional.get();

			if (employeeRequest.getEmpName() != null)
				employeeEntity.setEmpName(employeeRequest.getEmpName());

			if (employeeRequest.getGender() != null)
				employeeEntity.setGender(employeeRequest.getGender());

			if (employeeRequest.getRole() != null)
				employeeEntity.setRole(employeeRequest.getRole());

			if (employeeRequest.getSalary() != null)
				employeeEntity.setSalary(employeeRequest.getSalary());

			EmployeeEntity empEntity = employeeRepository.save(employeeEntity);

			return EmployeeResponse.builder().empId(empEntity.getEmpId()).empName(empEntity.getEmpName())
					.gender(empEntity.getGender()).role(empEntity.getRole()).salary(empEntity.getSalary()).build();
		}
	}

}
