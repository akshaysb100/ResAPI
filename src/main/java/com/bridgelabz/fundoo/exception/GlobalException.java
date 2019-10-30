package com.bridgelabz.fundoo.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bridgelabz.fundoo.utility.ErrorResponse;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(VerificationFailedException.class)
	public ResponseEntity<Response> existresponse(VerificationFailedException exception) {
	Response exceptionresponse = new Response(HttpStatus.OK.value(),exception.getMessage());
    System.out.println("exception : "+exception.getMessage());
	return new ResponseEntity<Response>(exceptionresponse, HttpStatus.OK);
	}
	
	@ExceptionHandler(UserDoesNotExistException.class)
	public ResponseEntity<Response> existresponse(UserDoesNotExistException exception) {
	Response exceptionresponse = new Response(HttpStatus.UNAUTHORIZED.value(),exception.getMessage());

	return new ResponseEntity<Response>(exceptionresponse, HttpStatus.UNAUTHORIZED);
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponse error = new ErrorResponse(LocalDateTime.now(), "Validation Failed", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
//	
//	  @ExceptionHandler(Exception.class)
//	    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
//	        List<String> details = new ArrayList<>();
//	        details.add(ex.getLocalizedMessage());
//	        ErrorResponse error = new ErrorResponse("Server Error", details);
//	        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
//	    }
//	 
//	    @ExceptionHandler(UserDoesNotExistException.class)
//	    public final ResponseEntity<Object> handleUserNotFoundException(UserDoesNotExistException ex, WebRequest request) {
//	        List<String> details = new ArrayList<>();
//	        details.add(ex.getLocalizedMessage());
//	        ErrorResponse error = new ErrorResponse("Record Not Found", details);
//	        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
//	    }
//	 
//	    
	


}
