package com.springboot.car_rental_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.car_rental_app.model.PassengerDetails;
import com.springboot.car_rental_app.repository.PassengerRepository;

@Service
public class PassengerService {
@Autowired
private PassengerRepository passengerRepository;

	public PassengerDetails addPassenger(PassengerDetails list) {
		return passengerRepository.save(list);
	}

}
