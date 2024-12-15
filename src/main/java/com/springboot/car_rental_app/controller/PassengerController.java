package com.springboot.car_rental_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.car_rental_app.exception.ResourceNotFoundException;
import com.springboot.car_rental_app.model.BookingDetail;
import com.springboot.car_rental_app.model.PassengerBookingDetails;
import com.springboot.car_rental_app.model.PassengerDetails;
import com.springboot.car_rental_app.service.AddressService;
import com.springboot.car_rental_app.service.BookingDetailService;
import com.springboot.car_rental_app.service.PassengerBookingService;
import com.springboot.car_rental_app.service.PassengerService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class PassengerController {
@Autowired 
private PassengerService passengerService;
@Autowired 
private PassengerBookingService pbs;
@Autowired
private BookingDetailService bds;



@PostMapping("/add/passenger/{id}")
public ResponseEntity<?> addPassenger(@PathVariable int id,@RequestBody PassengerDetails pd) {
	 try {
	     pd = passengerService.addPassenger(pd);
		BookingDetail bd = bds.validate(id);
		PassengerBookingDetails obj = new PassengerBookingDetails();
		obj.setBooking(bd);
		obj.setPassenger(pd);
		 obj = pbs.PassengerBookingDetails(obj);
		return ResponseEntity.ok(obj);
		
		
	} catch (ResourceNotFoundException e) {
		return ResponseEntity.badRequest().body(e.getMssg());
	}
	
}
}
