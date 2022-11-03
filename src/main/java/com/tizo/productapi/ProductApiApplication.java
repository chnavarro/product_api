package com.tizo.productapi;

import com.tizo.productapi.dto.NewUserDTO;
import com.tizo.productapi.service.impl.UserServiceImpl;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Spring boot application API
 * @author cnavarro
 * @version 1.0
 * @since 2022-11-03
 */
@SpringBootApplication
public class ProductApiApplication {
	@Autowired
	private UserServiceImpl userService;

	public static void main(String[] args) {
		SpringApplication.run(ProductApiApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			userService.create(new NewUserDTO("admin", "Administrator", "adminPassword",null, "ADMIN", true));
			userService.create(new NewUserDTO("tester", "User Tester", "testerPassword", null, "USER", true));
		};
	}

}
