package com.springboot.car_rental_app.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.car_rental_app.model.BookingDetail;

public interface BookingDetailRepository extends JpaRepository<BookingDetail , Integer> {
    
	@Query("select bd from BookingDetail bd join bd.car c where c.id=?1")
	Optional<BookingDetail> getCar(int car_id);

    @Query("select bd from BookingDetail bd join bd.car c where c.id=?1")
	Optional<BookingDetail> dummy(int id);
  
    @Query("SELECT bd FROM BookingDetail bd " +
    	       "JOIN bd.car c " +
    	       "WHERE c.id = ?1 " +
    	       "AND ( (?2 BETWEEN bd.pickup_date AND bd.return_date) " +
    	       "   OR (?3 BETWEEN bd.pickup_date AND bd.return_date) " +
    	       "   OR (bd.pickup_date BETWEEN ?2 AND ?3))")
  	Optional<BookingDetail> searchCar(int car_id, LocalDate pickupDate,LocalDate dropDate);

    
    @Query("select bd from BookingDetail bd join bd.user u where u.id=?1")
	Page<BookingDetail> getBooking(int id, Pageable pageable);

    
    @Query("select bd from BookingDetail bd join bd.car c where c.id = ?1")
	List<BookingDetail> getBookingDetail(int id);

   
    @Query("select bd "
    		+ "from BookingDetail bd "
    		+ "join bd.car c "
    		+ "join bd.user u "
    		+ "where u.id=?1 and c.car_name=?2")
	List<BookingDetail> getByCar(int id, String carName);
}

/*
 select 
 from booking_detail bd Right Outer join car c 
 where bd.car_id = c.car_id
 */