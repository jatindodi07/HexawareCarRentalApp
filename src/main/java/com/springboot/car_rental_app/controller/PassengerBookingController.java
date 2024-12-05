package com.springboot.car_rental_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.car_rental_app.exception.ResourceNotFoundException;
import com.springboot.car_rental_app.model.BookingDetail;
import com.springboot.car_rental_app.model.PassengerDetails;
import com.springboot.car_rental_app.service.BookingDetailService;
import com.springboot.car_rental_app.service.PassengerBookingService;

@RestController
public class PassengerBookingController {
	@Autowired
	private PassengerBookingService pbs;
	@Autowired
	private BookingDetailService bookingDetailService;
	
	@GetMapping("/passenger/details-on-booking/{id}")
 	public ResponseEntity<?> getPassengerList(@PathVariable int id){
		try {
			BookingDetail obj = bookingDetailService.validate(id);
			List<PassengerDetails> list = pbs.getPassengerList(obj);
			return ResponseEntity.ok(list);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMssg());
			}
	}

}
