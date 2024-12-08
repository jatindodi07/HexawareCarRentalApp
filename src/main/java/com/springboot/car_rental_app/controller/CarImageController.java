package com.springboot.car_rental_app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.car_rental_app.exception.ResourceNotFoundException;
import com.springboot.car_rental_app.model.Car;
import com.springboot.car_rental_app.model.CarImage;
import com.springboot.car_rental_app.service.CarImageService;
import com.springboot.car_rental_app.service.CarService;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
public class CarImageController {
@Autowired
private CarImageService imageService;
@Autowired 
private CarService carService;


@PostMapping("/api/upload/car/image/{id}")
public CarImage uploadImage(@PathVariable int id ,@RequestParam MultipartFile file) throws IOException, ResourceNotFoundException {
	return imageService.uploadImage(id,file);
}
@GetMapping("/api/get/car/image/{id}")
public List<CarImage> getImage(@PathVariable int id) {
	Car car = carService.getCar(id);
	List<CarImage> carImage = imageService.find();	
	List<CarImage> iList =
			carImage.stream()
				.filter(i->i.getCar().getId() == car.getId())
				.toList();
	   
	return iList;
	}

	
}
	
	

