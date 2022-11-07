package com.example.productapi.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author cnavarro
 * @version 1.0
 * @since 2022-11-03
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {

    private Long productId;
    private String productName;
    private BigDecimal productCost;
    private BigDecimal productPrice;
    private String productTags;
    private Date productCreateDate;
    private Date productLastUpdate;
    private Boolean productStatus;
    @JsonBackReference
    private CategoryDTO category;

}
