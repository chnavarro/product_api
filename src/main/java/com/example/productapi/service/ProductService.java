package com.example.productapi.service;

import com.example.productapi.dto.NewProductDTO;
import com.example.productapi.dto.ProductDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cnavarro
 * @version 1.0
 * @since 2022-11-03
 */
public interface ProductService {

    @Transactional
    ProductDTO create(NewProductDTO newProductDTO);

    @Transactional
    ProductDTO update(NewProductDTO newProductDTO, Long productId);

    @Transactional
    void delete(Long productId);

    @Transactional(readOnly = true)
    List<ProductDTO> getAll();

    @Transactional(readOnly = true)
    ProductDTO findByProductById(Long productId);
}
