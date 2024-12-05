package com.springboot.car_rental_app.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.car_rental_app.dto.FilterDto;
import com.springboot.car_rental_app.exception.ResourceNotFoundException;
import com.springboot.car_rental_app.model.BookingDetail;
import com.springboot.car_rental_app.repository.BookingDetailRepository;
@Service
public class BookingDetailService {

	@Autowired
	private BookingDetailRepository bdr;
	public BookingDetail addBooking(BookingDetail bd) {
		// TODO Auto-generated method stub
		return bdr.save(bd);
	}
	public BookingDetail validate(int id) throws ResourceNotFoundException {
		Optional<BookingDetail> optional = bdr.findById(id) ;
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Booking_id is invalid");
		}
		return optional.get();
	}
	public Optional<BookingDetail> getCar(int car_id) {
		return bdr.getCar(car_id);
	}
	public Optional<BookingDetail> searchCar(int car_id, String city, String pickupDate) {
		LocalDate date = LocalDate.parse(pickupDate);
		Optional<BookingDetail> op = java.util.Optional.empty();
		try {
			System.out.println("Entering in to jpql");
		 op =bdr.searchCar(car_id,city,date);
		 System.out.println("Successful");
		   return op;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

	return op;
	
	}
	
	public BookingDetail dummy(int id) throws ResourceNotFoundException {
		return bdr.dummy(id).orElseThrow(()-> new ResourceNotFoundException("Car id is invalid"));
	}
	
	
	public Optional<BookingDetail> searchCarUsingDto(int id, String city,LocalDate date) {
		return bdr.searchCar(id, city, date);
	}
	
	
	
	
	
	
	
}
