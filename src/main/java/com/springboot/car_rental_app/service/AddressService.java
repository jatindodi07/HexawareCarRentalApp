package com.springboot.car_rental_app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.car_rental_app.exception.ResourceNotFoundException;
import com.springboot.car_rental_app.model.Address;
import com.springboot.car_rental_app.repository.AddressRepository;

@Service
public class AddressService {
@Autowired
private AddressRepository ar;

	public Address addAddress(Address add) {
		
		return ar.save(add);
	}
	public Address validateId(int id) throws ResourceNotFoundException{
		Optional<Address> adr = ar.findById(id);
		if(adr.isEmpty())throw new ResourceNotFoundException("Location_id Does not exixst");
		return adr.get();
	}

}
