package com.example.productapi.service;

import com.example.productapi.dto.NewCategoryDTO;
import com.example.productapi.dto.CategoryDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cnavarro
 * @version 1.0
 * @since 2022-11-03
 */
public interface CategoryService {

    @Transactional
    CategoryDTO create(NewCategoryDTO newCategoryDTO);

    @Transactional
    CategoryDTO update(NewCategoryDTO newCategoryDTO, Long categoryId);

    @Transactional
    void delete(Long categoryId);

    @Transactional(readOnly = true)
    List<CategoryDTO> getAll();

    @Transactional(readOnly = true)
    CategoryDTO findByCategoryId(Long categoryId);

    @Transactional
    void deleteAll();
}
