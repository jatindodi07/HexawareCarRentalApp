package com.springboot.car_rental_app.dto;

import org.springframework.stereotype.Component;


import com.springboot.car_rental_app.model.Address;

@Component
public class UserUpdateDto {

	private String name;
	private String username;
	private Address address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
}
