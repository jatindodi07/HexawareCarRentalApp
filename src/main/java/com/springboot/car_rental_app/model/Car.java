package com.springboot.car_rental_app.model;
import com.springboot.car_rental_app.enums.CarType;
import com.springboot.car_rental_app.enums.Purpose;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int car_id;
	@Enumerated(EnumType.STRING)
	private CarType car_type;
	private String car_company;
	private String car_name;
	
	private String model;
	private int year;
	private int driven_km;
	private double mileage;
	@Enumerated(EnumType.STRING)
	private Purpose purpose;
	@ManyToOne
	private User user;
	public Purpose getPurpose() {
	return purpose;
}
public void setPurpose(Purpose purpose) {
	this.purpose = purpose;
}

	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getCar_id() {
		return car_id;
	}
	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}

	public String getCar_company() {
		return car_company;
	}
	public void setCar_company(String car_company) {
		this.car_company = car_company;
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
	public CarType getCar_type() {
		return car_type;
	}
	public void setCar_type(CarType car_type) {
		this.car_type = car_type;
	}

	public int getDriven_km() {
		return driven_km;
	}
	public void setDriven_km(int driven_km) {
		this.driven_km = driven_km;
	}
	public double getMileage() {
		return mileage;
	}
	public void setMileage(double mileage) {
		this.mileage = mileage;
		
	}

}