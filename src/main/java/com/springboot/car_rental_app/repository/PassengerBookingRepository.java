package com.springboot.car_rental_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.car_rental_app.model.BookingDetail;
import com.springboot.car_rental_app.model.PassengerBookingDetails;
import com.springboot.car_rental_app.model.PassengerDetails;

public interface PassengerBookingRepository extends JpaRepository<PassengerBookingDetails,Integer> {
  @Query("select p from PassengerBookingDetails pbd join pbd.booking b join pbd.passenger p where b=?1")
	List<PassengerDetails> getPassengerList(BookingDetail obj);

}
