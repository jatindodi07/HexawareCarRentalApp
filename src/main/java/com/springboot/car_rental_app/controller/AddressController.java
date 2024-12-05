package com.springboot.car_rental_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.car_rental_app.model.Address;
import com.springboot.car_rental_app.service.AddressService;

@RestController
public class AddressController {
@Autowired
private AddressService as;
@PostMapping("/add/address")
public Address addAddress(@RequestBody Address add) {
	return as.addAddress(add);
}

}
