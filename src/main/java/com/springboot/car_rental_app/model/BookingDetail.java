package com.springboot.car_rental_app.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class BookingDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int booking_id;
	private LocalDate pickup_date;
	private LocalDate return_date;
	@ManyToOne
	private Car car;
	@ManyToOne
	private User user;
	private double price;

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	public LocalDate getPickup_date() {
		return pickup_date;
	}
	public void setPickup_date(LocalDate pickup_date) {
		this.pickup_date = pickup_date;
	}
	public LocalDate getReturn_date() {
		return return_date;
	}
	public void setReturn_date(LocalDate return_date) {
		this.return_date = return_date;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public BookingDetail(int booking_id, LocalDate pickup_date, LocalDate return_date, Car car, User user,
			double price) {
		super();
		this.booking_id = booking_id;
		this.pickup_date = pickup_date;
		this.return_date = return_date;
		this.car = car;
		this.user = user;
		this.price = price;
	}
	public BookingDetail() {
		super();
	}


}
