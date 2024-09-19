package com.employee.version.v1.entities;

import java.math.BigDecimal;

import com.employee.version.v1.constants.EmployeeConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = EmployeeConstants.EMPLOYEE_TABLE)
@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {

	@Id
	@Column(name = EmployeeConstants.EMPLOYEE_ID)
	@GeneratedValue(strategy = GenerationType.UUID)
//	@GeneratedValue(strategy = GenerationType.UUID, generator = "generator") 
//	@GenericGenerator(name = "generator", strategy = "identity", parameters = {})

	private String empId;

	@Column(name = EmployeeConstants.EMPLOYEE_NAME)
	private String empName;

	@Column(name = EmployeeConstants.GENDER)
	private String gender;

	@Column(name = EmployeeConstants.SALARY)
	private BigDecimal salary;

//	@Transient
	@Column(name = EmployeeConstants.ROLE)
	private String role;

}
