package com.springboot.car_rental_app.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class BookingDetailDto {
private int user_id;
private int car_id;
private LocalDate pickupDate;
private LocalDate return_date;
private Double totalPrice;
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public int getCar_id() {
	return car_id;
}
public void setCar_id(int car_id) {
	this.car_id = car_id;
}


public LocalDate getPickupDate() {
	return pickupDate;
}
public void setPickupDate(LocalDate pickupDate) {
	this.pickupDate = pickupDate;
}

public LocalDate getReturn_date() {
	return return_date;
}
public void setReturn_date(LocalDate return_date) {
	this.return_date = return_date;
}
public Double getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(Double totalPrice) {
	this.totalPrice = totalPrice;
}



}
