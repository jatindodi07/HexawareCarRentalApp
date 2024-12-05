package com.springboot.car_rental_app.controller;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.car_rental_app.enums.CarType;
import com.springboot.car_rental_app.enums.Purpose;
import com.springboot.car_rental_app.exception.ResourceNotFoundException;
import com.springboot.car_rental_app.model.Car;
import com.springboot.car_rental_app.model.User;
import com.springboot.car_rental_app.service.CarService;
import com.springboot.car_rental_app.service.UserService;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
public class CarController {
	@Autowired
	private UserService userService;
	@Autowired
	private CarService carService;

	
	
	@PostMapping("/add/car/{id}")
	public ResponseEntity<?> addCar(@PathVariable int id , @RequestBody Car car) throws ResourceNotFoundException {
	
			User user = userService.validate(id);

			car.setUser(user);
	
			
			car = carService.addCar(car);
			
			
			return ResponseEntity.ok(car);
		
		
	}
	//cars available for renting on the particular date
	
	@GetMapping("/get/available/cars")
	public List<Car> getCarsAvailable(){
		List<Car> cars= carService.getCarsAvailable();
		return cars;
	}
	@GetMapping("api/get/car/type")
	public List<CarType> getCarType(){
		return Arrays.asList(CarType.values());
	}
	@GetMapping("api/get/car/purpose")
	public List<Purpose> getCarPurpose(){
		return Arrays.asList(Purpose.values());
	}
	
	
}
