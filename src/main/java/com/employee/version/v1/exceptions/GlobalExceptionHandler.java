package com.employee.version.v1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.employee.version.v1.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ErrorResponse handleException(Exception exception) {

		return ErrorResponse.builder().errorCode("5003").status(HttpStatus.CONFLICT).message(exception.getMessage())
				.build();

	}

}
