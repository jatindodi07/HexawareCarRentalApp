package com.springboot.car_rental_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.car_rental_app.model.Car;
import com.springboot.car_rental_app.model.RentalPrice;
import com.springboot.car_rental_app.repository.RentalRepository;

@Service
public class RentalService {
	@Autowired
	private RentalRepository rentalRepository;

	public RentalPrice addRentalPrice(RentalPrice obj) {
		return rentalRepository.save(obj);
	}

	public RentalPrice fetchRentalPrice(Car obj) {
		return rentalRepository.fetchRentalPrice(obj);
	}
	

}
