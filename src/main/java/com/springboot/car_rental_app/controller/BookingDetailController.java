package com.springboot.car_rental_app.controller;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.car_rental_app.dto.FilterDto;
import com.springboot.car_rental_app.dto.ResponseMessageDto;
import com.springboot.car_rental_app.exception.ResourceNotFoundException;
import com.springboot.car_rental_app.model.BookingDetail;
import com.springboot.car_rental_app.model.Car;
import com.springboot.car_rental_app.model.User;
import com.springboot.car_rental_app.service.BookingDetailService;
import com.springboot.car_rental_app.service.CarService;
import com.springboot.car_rental_app.service.UserService;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
public class BookingDetailController {
	@Autowired
	private UserService userService;
	@Autowired
	private CarService carService;
	@Autowired
	private BookingDetailService bds;
	
@PostMapping("/add/booking/{user_id}/{car_id}")
public ResponseEntity<?> addBooking(@PathVariable int user_id ,@PathVariable int car_id , @RequestBody BookingDetail bd) {
	
	try {
		User user = userService.validate(user_id);
		Car car = carService.validate(car_id);
		bd.setCar(car);
		bd.setUser(user);
		bd = bds.addBooking(bd);
		return ResponseEntity.ok(bd);
		
	} 
	catch (ResourceNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return ResponseEntity.badRequest().body(e.getMssg());
	
}
}

@GetMapping("/api/get/car")
public ResponseMessageDto getCar(@RequestParam int car_id , @RequestParam String city,@RequestParam String pickupDate, ResponseMessageDto dto) {
	System.out.println(car_id);
	Optional<BookingDetail> op = bds.getCar(car_id);
	
	if(op.isEmpty()) {
		 dto.setMsg("Car is Available For Booking 1 time");
		 return dto;
	}
	else {
		op = bds.searchCar(car_id,city,pickupDate);
		if(!op.isEmpty()){
			 dto.setMsg("Car is Available For Booking");
			 return dto;
		}
	    
		dto.setMsg("Not Available For Booking 2 time");
		return dto;
	}
	/*
	op = bds.searchCar(car_id,city,pickupDate);
	if(!op.isEmpty()){
		 dto.setMsg("Car is Available For Booking");
		 return dto;
	}
    
	dto.setMsg("Not Available For Booking 2 time");
	return dto;

	*/
}
@GetMapping("test/api/booking")
public BookingDetail dummy(@RequestParam int id) throws ResourceNotFoundException {
	return bds.dummy(id);
}


@PostMapping("api/get/car/available")
public ResponseMessageDto getCar(@RequestBody FilterDto filterDto, ResponseMessageDto dto) {
	int id =filterDto.getCar_id();
	String city = filterDto.getCity();
	LocalDate pickupDate = LocalDate.parse(filterDto.getPickupDate());
	
	Optional<BookingDetail> op= bds.searchCarUsingDto(id,city,pickupDate);
	if(!op.isEmpty()){
		 dto.setMsg("Car is Available For Booking");
		 return dto;
	}
    
	dto.setMsg("Not Available For Booking 2 time");
	return dto;
}





















}
