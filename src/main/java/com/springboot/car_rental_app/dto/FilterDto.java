package com.springboot.car_rental_app.dto;

import org.springframework.stereotype.Component;

@Component
public class FilterDto {
 private String pickupDate;
 private String city;
 private int car_id;
public String getPickupDate() {
	return pickupDate;
}
public void setPickupDate(String pickupDate) {
	this.pickupDate = pickupDate;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public int getCar_id() {
	return car_id;
}
public void setCar_id(int car_id) {
	this.car_id = car_id;
}
 
}
