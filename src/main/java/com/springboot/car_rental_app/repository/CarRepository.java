package com.springboot.car_rental_app.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.car_rental_app.model.Car;

public interface CarRepository extends JpaRepository<Car,Integer> {
	@Query("select c from Car c join c.user u join u.address a where a.state = ?1 and a.city = ?2")
	List<Car> getCarsAvailableV2(String state, String city);

	@Query("select c from Car c join c.user u where u.id = ?1")
	List<Car> getCarByUserId(int id);

}
