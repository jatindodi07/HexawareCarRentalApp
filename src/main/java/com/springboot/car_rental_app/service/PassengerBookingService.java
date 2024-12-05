package com.springboot.car_rental_app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.car_rental_app.model.Address;
import com.springboot.car_rental_app.model.BookingDetail;
import com.springboot.car_rental_app.model.PassengerBookingDetails;
import com.springboot.car_rental_app.model.PassengerDetails;
import com.springboot.car_rental_app.repository.PassengerBookingRepository;

@Service
public class PassengerBookingService {
	@Autowired
	private PassengerBookingRepository pbr;

	public PassengerBookingDetails PassengerBookingDetails(PassengerBookingDetails obj) {
		return pbr.save(obj);
	}

	public List<PassengerDetails> getPassengerList(BookingDetail obj) {
		return pbr.getPassengerList(obj);
	}
	


}
