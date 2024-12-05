package com.springboot.car_rental_app.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.car_rental_app.enums.RoleType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
//@Table(name ="user_info" )
public class User implements UserDetails {
@Id
@GeneratedValue(strategy =GenerationType.AUTO)
private int id;
private String name;
private String username;
private String Password;
private boolean enabled =true; 
@Enumerated(EnumType.STRING)
private RoleType role;
@ManyToOne
private Address address;

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Address getAddress() {
	return address;
}
public void setAddress(Address address) {
	this.address = address;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return Password;
}
public void setPassword(String password) {
	Password = password;
}
public RoleType getRole() {
	return role;
}
public void setRole(RoleType role) {
	this.role = role;
}
public void setEnabled(boolean enabled) {
	this.enabled = enabled;
}
@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
	List<GrantedAuthority> list = new ArrayList<>();
	list.add(authority);
	return list;
}
@Override
public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return true;
}
@Override
public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return true;
}
@Override
public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return true;
}
@Override
public boolean isEnabled() {
	// TODO Auto-generated method stub
	return enabled;
}

	
}
