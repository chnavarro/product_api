package com.tizo.productapi.controller;

import com.tizo.productapi.dto.NewProductDTO;
import com.tizo.productapi.dto.ProductDTO;
import com.tizo.productapi.service.ProductService;
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
@RequestMapping("/product")
@Validated
public class ProductController {

    private ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody @Valid NewProductDTO newProduct) {
        ProductDTO product = service.create(newProduct);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@RequestBody @Valid NewProductDTO newProduct) {
        ProductDTO product = service.update(newProduct, newProduct.getProductId());
        return new ResponseEntity<>(product, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("{prod_id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable("prod_id") Long productId) {
        ProductDTO prodDTO = service.findByProductById(productId);
        return new ResponseEntity<>(prodDTO, HttpStatus.OK);
    }
}
