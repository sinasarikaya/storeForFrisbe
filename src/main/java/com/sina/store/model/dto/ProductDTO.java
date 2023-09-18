package com.sina.store.model.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private BigDecimal stock;

    private BigDecimal price = BigDecimal.ZERO;

}
