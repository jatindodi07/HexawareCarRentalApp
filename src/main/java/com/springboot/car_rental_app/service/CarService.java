package com.springboot.car_rental_app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.car_rental_app.exception.ResourceNotFoundException;
import com.springboot.car_rental_app.model.Car;
import com.springboot.car_rental_app.repository.CarRepository;

@Service
public class CarService {
	@Autowired
	private CarRepository carRepository;
	public Car addCar(Car car) {
		return carRepository.save(car);
		
	}
	public Car validate(int car_id) throws ResourceNotFoundException {
		Optional<Car> optional = carRepository.findById(car_id) ;
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Car_Id invalid");
		}
		return optional.get();
	}
	public List<Car> getCarsAvailable() {
		return carRepository.findAll();
	}
	public List<Car> getCarsAvailableV2(String state, String city) {
		return carRepository.getCarsAvailableV2(state,city);
	}
	public Car getCar(int id) {
		return carRepository.findById(id).get();
	}
	public List<Car> getCarByUserId(int id) {
		return carRepository.getCarByUserId(id);
	}

}
