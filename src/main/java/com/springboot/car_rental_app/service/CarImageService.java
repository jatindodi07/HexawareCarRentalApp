package com.springboot.car_rental_app.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.car_rental_app.exception.ResourceNotFoundException;
import com.springboot.car_rental_app.model.Car;
import com.springboot.car_rental_app.model.CarImage;
import com.springboot.car_rental_app.repository.CarImageRepository;

@Service
public class CarImageService {
@Autowired
private CarService carService;
@Autowired
private CarImageRepository carImageRepository;

	public CarImage uploadImage(int id, MultipartFile file) throws IOException, ResourceNotFoundException {
		System.out.println(file.getOriginalFilename());
		String Location = "C:/Angular/my-angular-app/public/images";
		Path path = Path.of(Location, file.getOriginalFilename()); 
		
		try {
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw e; 
		}
		Car car = carService.getCar(id);
		CarImage carImage = new CarImage();
		carImage.setCar(car);
		carImage.setFileName(file.getOriginalFilename());
		carImage.setPath(path.toString());
		return carImageRepository.save(carImage);
		
		
		
		
		
		
	}

	public List<CarImage> find() {
		return carImageRepository.findAll();
	}

}
