package com.springboot.car_rental_app.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.springboot.car_rental_app.enums.CarType;
import com.springboot.car_rental_app.model.CarImage;

@Component
public class CarDto {
	private CarType car_type;
	private String car_company;
	private String car_name;
	
	private String model;
	private int year;
	
    List<CarImage> carImage;

	public CarType getCar_type() {
		return car_type;
	}

	public void setCar_type(CarType car_type) {
		this.car_type = car_type;
	}

	public String getCar_company() {
		return car_company;
	}

	public void setCar_company(String car_company) {
		this.car_company = car_company;
	}

	public String getCar_name() {
		return car_name;
	}

	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	

	public List<CarImage> getCarImage() {
		return carImage;
	}

	public void setCarImage(List<CarImage> carImage) {
		this.carImage = carImage;
	}
	
    
}
