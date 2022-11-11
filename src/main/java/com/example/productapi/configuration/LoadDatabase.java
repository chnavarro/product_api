package com.example.productapi.configuration;

import com.example.productapi.dto.CategoryDTO;
import com.example.productapi.dto.NewCategoryDTO;
import com.example.productapi.dto.NewProductDTO;
import com.example.productapi.dto.NewUserDTO;
import com.example.productapi.service.CategoryService;
import com.example.productapi.service.ProductService;
import com.example.productapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @Bean
    public CommandLineRunner initDatabase() {

        return args -> {
            log.info("Clear user table");
            userService.deleteAll();
            userService.create(new NewUserDTO("admin", "Administrator", "adminPassword",null, "ADMIN", true));
            userService.create(new NewUserDTO("tester", "User Tester", "testerPassword", null, "USER", true));

            log.info("Clear category and product table");
            categoryService.deleteAll();
            productService.deleteAll();

            log.info("Preloading categories");
            CategoryDTO categoryDTO = categoryService.create(new NewCategoryDTO(null, "Groceries", true));

            log.info("Preloading products");
            productService.create(new NewProductDTO(null, "Bread", new BigDecimal(10), new BigDecimal(15), "Bread", true, categoryDTO.getCategoryId()));
            productService.create(new NewProductDTO(null, "Banana", new BigDecimal(2), new BigDecimal(5), "Fruit", true, categoryDTO.getCategoryId()));
            productService.create(new NewProductDTO(null, "Milk", new BigDecimal(10), new BigDecimal(25), "Dairy", true, categoryDTO.getCategoryId()));
        };
    }
}
