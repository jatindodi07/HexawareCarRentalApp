package com.springboot.car_rental_app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.car_rental_app.dto.CarDto;
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
@GetMapping("/api/get/car/image")
public List<CarDto> getAllProducts() {
	List<Car> cList =  carService.getCarsAvailable();
	List<CarImage> imageList= imageService.find();
	
	List<CarDto> listDto = new ArrayList<>();
	for(Car c : cList) {
		CarDto dto = new CarDto();
		dto.setCar_company(c.getCar_company());
		dto.setCar_name(c.getCar_name());
		dto.setCar_type(c.getCar_type());
		dto.setModel(c.getModel());
		dto.setYear(c.getYear());
		
		
		List<CarImage> iList =
				imageList.stream()
					.filter(i->i.getCar().getId() == c.getId())
					.toList();
		dto.setCarImage(iList);
		listDto.add(dto);
	}
	
	return listDto;
}
	
}
	
	

