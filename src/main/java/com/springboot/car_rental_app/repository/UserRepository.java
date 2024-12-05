package com.springboot.car_rental_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.car_rental_app.enums.Purpose;
import com.springboot.car_rental_app.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from Car c join c.user u where c.purpose=?1")
	List<User> getUserInfo(Purpose purpose);

    Optional<User> findByUsername(String username);

}
