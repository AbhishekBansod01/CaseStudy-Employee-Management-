package com.employee.version.v1.request;

import java.math.BigDecimal;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Component
public class EmployeeRequest {

	private String empId;

	private String empName;

	private String gender;

	private BigDecimal salary;

	@Value("${default.value.role:Ab}")
	private String role;
}
