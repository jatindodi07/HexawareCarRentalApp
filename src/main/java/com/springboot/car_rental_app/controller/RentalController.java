package com.springboot.car_rental_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.car_rental_app.exception.ResourceNotFoundException;
import com.springboot.car_rental_app.model.Car;
import com.springboot.car_rental_app.model.RentalPrice;
import com.springboot.car_rental_app.service.CarService;
import com.springboot.car_rental_app.service.RentalService;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
public class RentalController {
	@Autowired
	private RentalService rentalService;
	@Autowired
	private CarService carService;
	
	
	@PostMapping("/api/add/rental-price/{id}")
	public ResponseEntity<?> addRentalPrice(@PathVariable int id,@RequestBody RentalPrice obj) {
		try {
			Car car = carService.validate(id);
			obj.setCar(car);
			obj = rentalService.addRentalPrice(obj);
			return ResponseEntity.ok(obj);
			
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMssg());
		}
	}
	
	@GetMapping("/fetch/rental/price/{id}")
	public ResponseEntity<?> fetchRentalPrice(@PathVariable int id) {
		try {
			 Car car = carService.validate(id);
			RentalPrice rp = rentalService.fetchRentalPrice(car);
			return ResponseEntity.ok(rp);
			
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMssg());
		}
	}

}
