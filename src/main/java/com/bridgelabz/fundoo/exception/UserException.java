package com.bridgelabz.fundoo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserException {
	
	@ExceptionHandler(UserDoesNotExistException.class)
	public ResponseEntity<ExceptionResponse> existresponse(UserDoesNotExistException exception) {
	ExceptionResponse exceptionresponse = new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(),exception.getMessage());

	return new ResponseEntity<ExceptionResponse>(exceptionresponse, HttpStatus.UNAUTHORIZED);
	}
}
