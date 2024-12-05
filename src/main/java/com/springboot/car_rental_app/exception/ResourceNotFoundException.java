package com.springboot.car_rental_app.exception;

public class ResourceNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
  private String mssg;
public ResourceNotFoundException(String mssg) {
	super();
	this.mssg = mssg;
}
public String getMssg() {
	return mssg;
}


  
}
