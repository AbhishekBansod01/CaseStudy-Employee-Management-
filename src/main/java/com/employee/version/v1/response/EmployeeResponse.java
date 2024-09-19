package com.employee.version.v1.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class EmployeeResponse {

	private String empId;

	private String empName;

	private String gender;

	private BigDecimal salary;

	private String role;
}
