package com.springboot.car_rental_app;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
@Configuration
public class OpenApiSwaggerConfig {
	
	@Bean
	OpenAPI getOpenAPi() {
		Info info = new Info();
		info.setTitle("Car Rental Documentation with Swagger");
		OpenAPI api = new OpenAPI();
		api.setInfo(info);
		
		return api;
	}
}