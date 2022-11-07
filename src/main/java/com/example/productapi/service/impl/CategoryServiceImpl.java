package com.example.productapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.productapi.dto.CategoryDTO;
import com.example.productapi.dto.NewCategoryDTO;
import com.example.productapi.exception.ResourceNotFoundException;
import com.example.productapi.model.Category;
import com.example.productapi.repository.CategoryRepository;
import com.example.productapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cnavarro
 * @version 1.0
 * @since 2022-11-03
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository repository;
    private ObjectMapper mapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Create and persist Category
     * @param newCategoryDTO - DTO represent a new Category
     * @return - CategoryDTO
     */
    @Override
    @Transactional
    public CategoryDTO create(NewCategoryDTO newCategoryDTO) {
        Category category = new Category();
        category.setCategoryName(newCategoryDTO.getCategoryName());
        category.setCategoryStatus(newCategoryDTO.getCategoryStatus());
        category = repository.save(category);
        return mapper.convertValue(category, CategoryDTO.class);
    }

    /**
     * Update a Category
     * @param newCategoryDTO - DTO represent a Category
     * @param categoryId - Category Id
     * @return CategoryDTO
     */
    @Override
    @Transactional
    public CategoryDTO update(NewCategoryDTO newCategoryDTO, Long categoryId) {
        Category category = repository.findByCategoryId(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category not found"));
        category.setCategoryName(newCategoryDTO.getCategoryName());
        category.setCategoryStatus(newCategoryDTO.getCategoryStatus());
        category.setCategoryLastUpdate(new Date());
        category = repository.save(category);
        return mapper.convertValue(category, CategoryDTO.class);
    }

    /**
     * Delete a Category
     * @param categoryId - Category Id
     */
    @Override
    @Transactional
    public void delete(Long categoryId) {
        Category category = repository.findByCategoryId(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category not found"));
        repository.delete(category);
    }

    /**
     * List all Categories
     * @return List<CategoryDTO>
     */
    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> getAll() {
        List<Category> categories = repository.findAll();
        return categories.stream().map(category -> mapper.convertValue(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Find a Category by Id
     * @param categoryId - category Id
     * @return CategoryDTO
     */
    @Override
    @Transactional(readOnly = true)
    public CategoryDTO findByCategoryId(Long categoryId) {
        Category category = repository.findByCategoryId(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Category not found"));
        return mapper.convertValue(category, CategoryDTO.class);
    }

    /**
     * Find all users and delete them
     */
    @Transactional
    public void deleteAll() {
        repository.deleteAll(repository.findAll());
    }
}
