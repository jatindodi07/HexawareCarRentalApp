package com.springboot.car_rental_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.car_rental_app.enums.Purpose;
import com.springboot.car_rental_app.exception.ResourceNotFoundException;
import com.springboot.car_rental_app.exception.UsernameFoundException;
import com.springboot.car_rental_app.model.User;
import com.springboot.car_rental_app.repository.UserRepository;

@Service
public class UserService {
@Autowired
private UserRepository userRepository;
@Autowired
private BCryptPasswordEncoder passEncoder;
	public User addUser(User user) throws UsernameFoundException {
		Optional<User> optional = userRepository.findByUsername(user.getUsername());
		if(!optional.isEmpty())throw new UsernameFoundException("Username Already exists");
		String encryptedPass = passEncoder.encode(user.getPassword());
		user.setPassword(encryptedPass);
		return userRepository.save(user);
	}
	
	public User validate(int id) throws ResourceNotFoundException {
		Optional<User> optional  = userRepository.findById(id);
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Invalid Id User Does not Exists");
		}
		return optional.get();
	}
	
	
	public List<User> getUserInfo(Purpose purpose) {
		return userRepository.getUserInfo(purpose);
	}
	
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username).get();
	}

	public User updateProfile(User user) {
		return userRepository.save(user);
	}
	public User addUserV2(User user)
	{
		return userRepository.save(user);
	}

}
