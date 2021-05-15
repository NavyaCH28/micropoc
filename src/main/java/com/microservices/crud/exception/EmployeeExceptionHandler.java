package com.microservices.crud.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler({Exception.class})
	public final ResponseEntity<Error> handleAllExceptions(Exception ex, WebRequest req) {
		Error e = Error.builder().
				code(HttpStatus.EXPECTATION_FAILED.name()).message(ex.getMessage()).build();
		
		return new ResponseEntity<Error>(e, HttpStatus.EXPECTATION_FAILED);
		
	}
	
	@ExceptionHandler({DataAccessException.class})
	public final ResponseEntity<Error> handleAllExceptions(Exception ex) {
		Error e = Error.builder().
				code(HttpStatus.INTERNAL_SERVER_ERROR.name()).message(ex.getMessage()).build();
		
		return new ResponseEntity<Error>(e, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}
