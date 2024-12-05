package com.springboot.car_rental_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private int adress_id;
private String city;
private String state;
private String pincode;
private String street;
private String house_no;
public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}

public int getAdress_id() {
	return adress_id;
}
public void setAdress_id(int adress_id) {
	this.adress_id = adress_id;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getPincode() {
	return pincode;
}
public void setPincode(String pincode) {
	this.pincode = pincode;
}
public String getHouse_no() {
	return house_no;
}
public void setHouse_no(String house_no) {
	this.house_no = house_no;
}

}
