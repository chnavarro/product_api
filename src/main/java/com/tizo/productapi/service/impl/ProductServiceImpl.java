package com.tizo.productapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tizo.productapi.dto.NewProductDTO;
import com.tizo.productapi.dto.ProductDTO;
import com.tizo.productapi.exception.ResourceNotFoundException;
import com.tizo.productapi.model.Product;
import com.tizo.productapi.repository.ProductRepository;
import com.tizo.productapi.service.ProductService;
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
public class ProductServiceImpl implements ProductService {

    private ProductRepository repository;
    private ObjectMapper mapper;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Create and persist a Product
     * @param newProductDTO - DTO represent a new Product
     * @return ProductDTO
     */
    @Override
    @Transactional
    public ProductDTO create(NewProductDTO newProductDTO) {
        Product product = new Product();
        product.setProductName(newProductDTO.getProductName());
        product.setProductCost(newProductDTO.getProductCost());
        product.setProductPrice(newProductDTO.getProductPrice());
        product.setProductTags(newProductDTO.getProductTags());
        product = repository.save(product);
        return mapper.convertValue(product, ProductDTO.class);
    }

    /**
     * Update a Product
     * @param newProductDTO - DTO represent a product
     * @param productId - product Id
     * @return ProductDTO
     */
    @Override
    @Transactional
    public ProductDTO update(NewProductDTO newProductDTO, Long productId) {
        Product product = repository.findByProductId(productId).orElseThrow(
                () -> new ResourceNotFoundException("User not found"));
        product.setProductName(newProductDTO.getProductName());
        product.setProductCost(newProductDTO.getProductCost());
        product.setProductPrice(newProductDTO.getProductPrice());
        product.setProductTags(newProductDTO.getProductTags());
        product.setProductStatus(newProductDTO.getProductStatus());
        product.setProductLastUpdate(new Date());
        product = repository.save(product);
        return mapper.convertValue(product, ProductDTO.class);
    }

    /**
     * Delete a Product
     * @param productId - product Id
     */
    @Override
    @Transactional
    public void delete(Long productId) {
        Product product = repository.findByProductId(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product not found"));
        repository.delete(product);
    }

    /**
     * List all products
     * @return - List<ProductDTO>
     */
    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getAll() {
        List<Product> products = repository.findAll();
        return products.stream().map(product -> mapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Find a product by Name (Id)
     * @param productId - Product Id
     * @return productDTO
     */
    @Override
    @Transactional(readOnly = true)
    public ProductDTO findByProductById(Long productId) {
        Product product = repository.findByProductId(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product not found"));
        return mapper.convertValue(product, ProductDTO.class);
    }
}
