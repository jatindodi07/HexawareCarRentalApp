package com.springboot.car_rental_app;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springboot.car_rental_app.service.UserSecurityService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserSecurityService userSecurityService;
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		 .csrf((csrf) -> csrf.disable())
		 .authorizeHttpRequests(authorize -> authorize
				 	.requestMatchers(HttpMethod.POST, "/api/token").permitAll()
				 	.requestMatchers(HttpMethod.POST, "/api/signup").permitAll() 
				 	.requestMatchers(HttpMethod.GET, "/api/login").authenticated()
					.requestMatchers(HttpMethod.GET, "/get/car/{id}").authenticated()
				 	.requestMatchers(HttpMethod.GET, "test/api/booking").authenticated()
				 	.requestMatchers(HttpMethod.GET, "/api/get/car").authenticated()
				 	.requestMatchers(HttpMethod.GET, "/get/available/cars/v2").authenticated()
				 	.requestMatchers(HttpMethod.GET, "api/get/car/type").authenticated()
				 	.requestMatchers(HttpMethod.GET, "api/get/car/purpose").authenticated()
				 	.requestMatchers(HttpMethod.GET, "/api/get/user").authenticated()//
				 	.requestMatchers(HttpMethod.GET, "/get/user/having/cars").hasAuthority("EXECUTIVE") 
				 	.requestMatchers(HttpMethod.POST, "/add/address").permitAll() 
					.requestMatchers(HttpMethod.POST, "/api/get/car/image/{id}").authenticated() 
					.requestMatchers(HttpMethod.POST, "/api/change/role").authenticated() 
				 	.requestMatchers(HttpMethod.POST, "/add/car/{id}").authenticated() 
					.requestMatchers(HttpMethod.POST, "/api/upload/car/image/{id}").authenticated() 
					.requestMatchers(HttpMethod.POST, "api/get/car/available").authenticated() 
				 	.requestMatchers(HttpMethod.POST, "/update/user").authenticated() 
					.requestMatchers(HttpMethod.GET, "/get/available/cars").authenticated()
					.requestMatchers(HttpMethod.POST, "/api/add/rental-price/{id}").authenticated()
					.requestMatchers(HttpMethod.GET, "/fetch/rental/price/{id}").authenticated()
					.requestMatchers(HttpMethod.GET, "/api/get/booking/{id}").authenticated()
					.requestMatchers(HttpMethod.POST, "/add/booking").authenticated()
					.requestMatchers(HttpMethod.POST, "/add/passenger/{id}").authenticated()
					.requestMatchers(HttpMethod.POST, "/api/get/booking/detail/{id}").authenticated()
					.requestMatchers(HttpMethod.GET, "/passenger/details-on-booking/{id}").authenticated()
				 	
				.anyRequest().permitAll()
			) 
			.sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			
		   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		 
		return http.build();
	}
	
	
	@Bean
	PasswordEncoder getEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
		return passEncoder;
	}
	@Bean
	AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider(){
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userSecurityService);
		authenticationProvider.setPasswordEncoder(getEncoder());
		return authenticationProvider;
	}
}
