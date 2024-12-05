package com.springboot.car_rental_app.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.car_rental_app.JwtUtil;
import com.springboot.car_rental_app.dto.Dto;
import com.springboot.car_rental_app.dto.UserUpdateDto;
import com.springboot.car_rental_app.enums.Purpose;
import com.springboot.car_rental_app.enums.RoleType;
import com.springboot.car_rental_app.exception.ResourceNotFoundException;
import com.springboot.car_rental_app.exception.UsernameFoundException;
import com.springboot.car_rental_app.model.Address;
import com.springboot.car_rental_app.model.User;
import com.springboot.car_rental_app.service.AddressService;
import com.springboot.car_rental_app.service.UserSecurityService;
import com.springboot.car_rental_app.service.UserService;
@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
public class UserController {
@Autowired
private UserService userService;
@Autowired
private AddressService ads;
@Autowired
private AuthenticationManager authenticationManager;
@Autowired
private JwtUtil jwtUtil;
@Autowired
private UserSecurityService userSecurityService;

@PostMapping("/api/token")
public ResponseEntity<?> getToken(@RequestBody User user, Dto dto ) {
	try {
	Authentication auth 
			= new UsernamePasswordAuthenticationToken
						(user.getUsername(), user.getPassword());
	
	authenticationManager.authenticate(auth);
	
	/*Check if username is in DB */
	user = (User) userSecurityService.loadUserByUsername(user.getUsername());
	
	String jwt = jwtUtil.generateToken(user.getUsername());
	dto.setUsername(user.getUsername());
	dto.setToken(jwt);
	return ResponseEntity.ok(dto);
	}
	catch(AuthenticationException ae) {
		return ResponseEntity.badRequest().body(ae.getMessage());
	}
}


@PostMapping("/api/signup/{id}")
public ResponseEntity<?> addUser(@PathVariable int id , @RequestBody User user) {
	
	try {
		Address ad = ads.validateId(id);
		user.setAddress(ad);
	   //user.setRole(RoleType.valueOf(user.getRole().toString().toUpperCase()));
		User u1 =  userService.addUser(user);
		return ResponseEntity.ok(u1);
	} catch (UsernameFoundException | ResourceNotFoundException e) {
	   
		return ResponseEntity.badRequest().body(e);
	}
}


@GetMapping("/api/login")
public ResponseEntity<?>  getLogin(Principal principal) {;
	String username;
	if(principal==null) {
		return ResponseEntity.badRequest().body("Principal is null");
	}
	else {
	 username= principal.getName();
		
	}
	   
		User user =userService.getUserByUsername(username);
		if(!user.isEnabled()) {
			return ResponseEntity.badRequest().body("User is disabled contact admin");
		}
		return ResponseEntity.ok(user);
		

}
@GetMapping("/api/get/user")
public UserUpdateDto getUser(Principal principal,UserUpdateDto udd) {
	String username = principal.getName();
	User user = userService.getUserByUsername(username);
	udd.setAddress(user.getAddress());
	udd.setName(user.getName());
	udd.setUsername(user.getUsername());
	return udd;
	
}


@PostMapping("/update/user")
public User updateProfile(@RequestBody User new_user ,Principal principal) {
	String username = principal.getName();
	User old = userService.getUserByUsername(username);
	old.setAddress(new_user.getAddress());
	old.setName(new_user.getName());
	old.setUsername(new_user.getUsername());
	return userService.updateProfile(old);
	
}
//executive can fetch records from this api

@GetMapping("/get/user/having/cars")
public List<User> getUserInfo(@RequestParam String type){
	Purpose purpose = Purpose.valueOf(type);
	return userService.getUserInfo(purpose);
}


}
