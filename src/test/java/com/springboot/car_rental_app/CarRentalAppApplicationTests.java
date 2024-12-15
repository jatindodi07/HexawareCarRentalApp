package com.springboot.car_rental_app;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.car_rental_app.enums.CarType;
import com.springboot.car_rental_app.enums.Purpose;
import com.springboot.car_rental_app.enums.RoleType;
import com.springboot.car_rental_app.model.Address;
import com.springboot.car_rental_app.model.BookingDetail;
import com.springboot.car_rental_app.model.Car;
import com.springboot.car_rental_app.model.User;
import com.springboot.car_rental_app.repository.BookingDetailRepository;
import com.springboot.car_rental_app.service.BookingDetailService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class CarRentalAppApplicationTests {

    @InjectMocks
    private BookingDetailService bookingDetailService;

    @Mock
    private BookingDetailRepository bdr;

    private BookingDetail bookingDetail;
    private Car car;

    @BeforeEach
    public void setUp() {
        
        Address ad = new Address(1, "dehradun", "uttarakhand", "248001", "nehru", "g45");
        User user = new User(1, "jatin", "jatin", "jatin", true, RoleType.RENTER, ad);
        car = new Car(1, CarType.SUV, "Toyota", "Fortuner", "2021 Model", 2021, 15000, 12.5, Purpose.RENTAL, user);
        bookingDetail = new BookingDetail(1, LocalDate.now(), LocalDate.now().plusDays(4), car, user, 2000);
    }

    @Test
    public void testAddBooking() {
        
        when(bdr.save(bookingDetail)).thenReturn(bookingDetail);

        BookingDetail newBooking = bookingDetailService.addBooking(bookingDetail);

        assertEquals(car, newBooking.getCar());
        verify(bdr, times(1)).save(bookingDetail);
    }
    @Test
    public void testBooking() {
        
        when(bdr.save(bookingDetail)).thenReturn(bookingDetail);

       
        BookingDetail newBooking = bookingDetailService.addBooking(bookingDetail);

      
        assertEquals(car, newBooking.getCar());
        verify(bdr, times(1)).save(bookingDetail);
    }
}

