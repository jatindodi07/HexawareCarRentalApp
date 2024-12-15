package com.springboot.car_rental_app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Optional<BookingDetail> searchCar(int car_id, String pickupDate,String dropDate) {
		LocalDate pick_date = LocalDate.parse(pickupDate);
		LocalDate drop_date = LocalDate.parse(dropDate);
		
		Optional<BookingDetail> op =bdr.searchCar(car_id,pick_date,drop_date);
		   return op;
	
	}
	public List<BookingDetail> getBooking(int id) throws ResourceNotFoundException {
		return bdr.getBooking(id);
	}
	public List<BookingDetail> getBookingDetail(int id) {
		return bdr.getBookingDetail(id);
	}

	
	
		
	
	
	
}
