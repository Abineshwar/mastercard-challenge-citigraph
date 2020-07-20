package com.codechallenge.mastercard.cityconnectionsApp.rest_controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codechallenge.mastercard.cityconnectionsApp.cityconnectionsService;

@RestController
@Validated
public class cityconnectionsController {
	
	@Autowired
	private cityconnectionsService cityservice;
	
	@RequestMapping("/connected")
	public ResponseEntity<String> isConnected(
			@RequestParam(required = false) final @NotEmpty @Valid String origin,
			@RequestParam(required = false) final @NotEmpty @Valid String destination) {
		//LOGGER.debug("isConnected is called with origin city = {} and destination city = {}", origin, destination);
		
		return new ResponseEntity<String>(cityservice.iscitiesconnected(origin,destination), HttpStatus.OK);
	}
	
	


	
}
