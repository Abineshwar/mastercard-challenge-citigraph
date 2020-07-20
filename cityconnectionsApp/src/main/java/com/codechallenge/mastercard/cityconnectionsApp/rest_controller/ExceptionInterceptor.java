package com.codechallenge.mastercard.cityconnectionsApp.rest_controller;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionInterceptor {
	@ExceptionHandler(ConstraintViolationException.class)
	  public final ResponseEntity<Object> handleConstraintViolationExceptions(
	      ConstraintViolationException ex) {
	    String exceptionResponse = String.format("Invalid input parameters: %s\n", ex.getMessage());
	    return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	  }
}
