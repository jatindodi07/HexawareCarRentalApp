package com.springboot.car_rental_app.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.car_rental_app.model.BookingDetail;

public interface BookingDetailRepository extends JpaRepository<BookingDetail , Integer> {
    
	@Query("select bd from BookingDetail bd join bd.car c where c.car_id=?1")
	Optional<BookingDetail> getCar(int car_id);

    @Query("select bd from BookingDetail bd join bd.car c where c.car_id=?1")
	Optional<BookingDetail> dummy(int id);
  
      @Query("select bd from BookingDetail bd join bd.car c join bd.user u join u.address a where c.car_id=?1and a.city=?2 and bd.return_date<?3")
     // @Query("select bd from BookingDetail bd join bd.car c join bd.user u join u.address a where c.car_id=1 and a.city='dehradun' and bd.return_date<'2024-04-16'")
  	Optional<BookingDetail> searchCar(int car_id, String city, LocalDate pickupDate);
}

/*
 select 
 from booking_detail bd Right Outer join car c 
 where bd.car_id = c.car_id
 */