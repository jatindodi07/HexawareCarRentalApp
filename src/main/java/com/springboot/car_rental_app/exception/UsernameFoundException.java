package com.springboot.car_rental_app.exception;

public class UsernameFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String getMssg() {
		return mssg;
	}
	public UsernameFoundException(String mssg) {
		super();
		this.mssg = mssg;
	}
	private String mssg;


}
