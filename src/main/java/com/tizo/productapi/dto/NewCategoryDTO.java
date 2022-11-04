package com.tizo.productapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class NewCategoryDTO {

    private Long categoryId;
    private String categoryName;
    private Boolean categoryStatus;

}
