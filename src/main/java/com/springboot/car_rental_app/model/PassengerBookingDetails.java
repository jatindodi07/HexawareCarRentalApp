package com.springboot.car_rental_app.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class PassengerBookingDetails {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
@ManyToOne
private PassengerDetails passenger;
@ManyToOne
private BookingDetail booking;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public PassengerDetails getPassenger() {
	return passenger;
}
public void setPassenger(PassengerDetails passenger) {
	this.passenger = passenger;
}
public BookingDetail getBooking() {
	return booking;
}
public void setBooking(BookingDetail booking) {
	this.booking = booking;
}



}
