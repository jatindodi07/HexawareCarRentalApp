package com.springboot.car_rental_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.car_rental_app.model.Car;
import com.springboot.car_rental_app.model.RentalPrice;

public interface RentalRepository extends JpaRepository<RentalPrice,Integer>{
   @Query("select rp from RentalPrice rp join rp.car c where c= ?1")
	RentalPrice fetchRentalPrice(Car obj);

}
