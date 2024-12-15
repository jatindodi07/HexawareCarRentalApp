package com.springboot.car_rental_app;

import com.springboot.car_rental_app.model.Address;
import com.springboot.car_rental_app.model.Car;
import com.springboot.car_rental_app.model.User;
import com.springboot.car_rental_app.repository.CarRepository;
import com.springboot.car_rental_app.service.CarService;
import com.springboot.car_rental_app.enums.CarType;
import com.springboot.car_rental_app.enums.Purpose;
import com.springboot.car_rental_app.enums.RoleType;
import com.springboot.car_rental_app.exception.ResourceNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.List;

@SpringBootTest
class CarServiceTests {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    private Car car;

    @BeforeEach
    public void setUp() {
    	Address ad = new Address(1, "dehradun", "uttarakhand", "248001", "nehru", "g45");
        User user = new User(1, "jatin", "jatin", "jatin", true, RoleType.RENTER, ad);
        car = new Car(1, CarType.SUV, "Toyota", "Fortuner", "2021 Model", 2021, 15000, 12.5, Purpose.RENTAL, user);
    }

    @Test
    public void testAddCar() {
       
        when(carRepository.save(car)).thenReturn(car);

        Car newCar = carService.addCar(car);

        assertEquals(car, newCar);
        verify(carRepository, times(1)).save(car);
    }

    @Test
    public void testValidateCarExists() throws ResourceNotFoundException {
        // Mock repository behavior
        when(carRepository.findById(1)).thenReturn(Optional.of(car));

        Car validatedCar = carService.validate(1);

        assertEquals(car, validatedCar);
        verify(carRepository, times(1)).findById(1);
    }

    @Test
    public void testValidateCarNotFound() {
        when(carRepository.findById(999)).thenReturn(Optional.empty());

        try {
            carService.validate(999);
            
        } catch (ResourceNotFoundException e) {
            assertEquals("Car_Id invalid", e.getMssg());
        }

        verify(carRepository, times(1)).findById(999);
    }

    @Test
    public void testGetCarsAvailable() {
       
        when(carRepository.findAll()).thenReturn(List.of(car));

        List<Car> cars = carService.getCarsAvailable();

        assertEquals(1, cars.size());
        assertEquals(car, cars.get(0));
        verify(carRepository, times(1)).findAll();
    }

    @Test
    public void testGetCarsAvailableV2() {
  
        when(carRepository.getCarsAvailableV2("Uttarakhand", "Dehradun")).thenReturn(List.of(car));

        List<Car> cars = carService.getCarsAvailableV2("Uttarakhand", "Dehradun");

        assertEquals(1, cars.size());
        assertEquals(car, cars.get(0));
        verify(carRepository, times(1)).getCarsAvailableV2("Uttarakhand", "Dehradun");
    }

    @Test
    public void testGetCar() {
                when(carRepository.findById(1)).thenReturn(Optional.of(car));

        Car foundCar = carService.getCar(1);

        assertEquals(car, foundCar);
        verify(carRepository, times(1)).findById(1);
    }

    @Test
    public void testGetCarNotFound() {
       
        when(carRepository.findById(999)).thenReturn(Optional.empty());
        try {
            carService.validate(999);
        } catch (ResourceNotFoundException e) {
            assertEquals("Car_Id invalid", e.getMssg());
        }

        verify(carRepository, times(1)).findById(999);
    }

    @Test
    public void testGetCarByUserId() {
     
        when(carRepository.getCarByUserId(1)).thenReturn(List.of(car));


        List<Car> cars = carService.getCarByUserId(1);

    
        assertEquals(1, cars.size());
        assertEquals(car, cars.get(0));
        verify(carRepository, times(1)).getCarByUserId(1);
    }
}
