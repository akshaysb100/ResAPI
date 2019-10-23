package com.bridgelabz.fundoo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(UserDoesNotExistException.class)
	public ResponseEntity<Response> existresponse(UserDoesNotExistException exception) {
	Response exceptionresponse = new Response(HttpStatus.UNAUTHORIZED.value(),exception.getMessage());

	return new ResponseEntity<Response>(exceptionresponse, HttpStatus.UNAUTHORIZED);
	}
}
