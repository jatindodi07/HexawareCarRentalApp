package com.springboot.car_rental_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.springboot.car_rental_app.dto.ResponseMessageDto;
import com.springboot.car_rental_app.exception.ResourceNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private ResponseMessageDto dto; 
	
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e){
		 dto.setMsg(e.getMssg());
		 return ResponseEntity.badRequest().body(dto);
	}
}