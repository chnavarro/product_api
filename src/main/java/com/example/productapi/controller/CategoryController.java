package com.example.productapi.controller;

import com.example.productapi.dto.NewCategoryDTO;
import com.example.productapi.dto.CategoryDTO;
import com.example.productapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author cnavarro
 * @version 1.0
 * @since 2022-11-03
 */
@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {

    private CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody @Valid NewCategoryDTO newCategory) {
        CategoryDTO category = service.create(newCategory);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> update(@RequestBody @Valid NewCategoryDTO newCategory) {
        CategoryDTO category = service.update(newCategory, newCategory.getCategoryId());
        return new ResponseEntity<>(category, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("{cat_id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable("cat_id") Long categoryId) {
        CategoryDTO catDTO = service.findByCategoryId(categoryId);
        return new ResponseEntity<>(catDTO, HttpStatus.OK);
    }
}
