package com.example.productapi;

import com.example.productapi.dto.CategoryDTO;
import com.example.productapi.dto.NewCategoryDTO;
import com.example.productapi.dto.NewProductDTO;
import com.example.productapi.dto.NewUserDTO;
import com.example.productapi.service.CategoryService;
import com.example.productapi.service.ProductService;
import com.example.productapi.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

/**
 * Spring boot application API
 * @author cnavarro
 * @version 1.0
 * @since 2022-11-03
 */
@SpringBootApplication
public class ProductApiApplication {
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;

	public static void main(String[] args) {
		SpringApplication.run(ProductApiApplication.class, args);
	}

	@Bean
	InitializingBean sendDatabase() {
		return () -> {
			userService.create(new NewUserDTO("admin", "Administrator", "adminPassword",null, "ADMIN", true));
			userService.create(new NewUserDTO("tester", "User Tester", "testerPassword", null, "USER", true));

			//TODO - Fix for duplicate categories
			CategoryDTO categoryDTO = categoryService.create(new NewCategoryDTO(null, "Groceries", true));

			productService.create(new NewProductDTO(null, "Bread", new BigDecimal(10), new BigDecimal(15), "Bread", true, categoryDTO.getCategoryId()));
			productService.create(new NewProductDTO(null, "Banana", new BigDecimal(2), new BigDecimal(5), "Fruit", true, categoryDTO.getCategoryId()));
			productService.create(new NewProductDTO(null, "Milk", new BigDecimal(10), new BigDecimal(25), "Dairy", true, categoryDTO.getCategoryId()));
		};
	}

}
