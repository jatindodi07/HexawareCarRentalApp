package com.springboot.car_rental_app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.car_rental_app.dto.BookingDetailDto;
import com.springboot.car_rental_app.dto.ResponseMessageDto;
import com.springboot.car_rental_app.exception.ResourceNotFoundException;
import com.springboot.car_rental_app.model.BookingDetail;
import com.springboot.car_rental_app.model.Car;
import com.springboot.car_rental_app.model.User;
import com.springboot.car_rental_app.service.BookingDetailService;
import com.springboot.car_rental_app.service.CarService;
import com.springboot.car_rental_app.service.UserService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
public class BookingDetailController {
	@Autowired
	private UserService userService;
	@Autowired
	private CarService carService;
	@Autowired
	private BookingDetailService bds;

	@PostMapping("/add/booking")
	public ResponseEntity<?> addBooking(@RequestBody BookingDetailDto dto) {

		try {
			BookingDetail bd = new BookingDetail();
			User user = userService.validate(dto.getUser_id());
			Car car = carService.validate(dto.getCar_id());
			bd.setCar(car);
			bd.setUser(user);
			bd.setPickup_date(dto.getPickupDate());
			bd.setReturn_date(dto.getReturn_date());
			bd.setPrice(dto.getTotalPrice());
			bd = bds.addBooking(bd);
			return ResponseEntity.ok(bd);

		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResponseEntity.badRequest().body(e.getMssg());

		}
	}

	@GetMapping("/api/get/car")
	public ResponseMessageDto getCar(@RequestParam int car_id, @RequestParam String pickupDate,
			@RequestParam String dropDate, ResponseMessageDto dto) {
		Optional<BookingDetail> op = bds.getCar(car_id);

		if (op.isEmpty()) {
			dto.setMsg("Car is Available For Booking");
			return dto;
		} else {
			op = bds.searchCar(car_id, pickupDate, dropDate);
			if (!op.isEmpty()) {
				dto.setMsg("Not Available For Booking");
				return dto;
			}
			dto.setMsg("Car is Available For Booking");
			return dto;
		}

	}

	@GetMapping("/api/get/booking/{id}")
	public Page<BookingDetail> getBooking(@PathVariable int id,@RequestParam(required=false,defaultValue="0") String page,@RequestParam(required=false,defaultValue="100000") String size) throws ResourceNotFoundException {
		Pageable pageable=null;
		try {
			pageable =PageRequest.of(Integer.parseInt(page),Integer.parseInt(size));
		}catch(Exception e) {
			throw e;
		}
		return bds.getBooking(id,pageable);

	}

@GetMapping("/api/get/booking/car/{id}")
public List<BookingDetail>getByCar(@PathVariable int id , @RequestParam String carName){
	return bds.getByCar(id,carName);
}

@GetMapping("/api/get/booking/detail/{id}")
public List<BookingDetail> getBookingDetail(@PathVariable int id){
	return bds.getBookingDetail(id);
}



















}
