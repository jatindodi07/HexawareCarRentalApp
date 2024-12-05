package com.springboot.car_rental_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.car_rental_app.model.Address;

public interface AddressRepository extends JpaRepository<Address,Integer> {

}
