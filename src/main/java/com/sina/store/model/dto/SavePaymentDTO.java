package com.sina.store.model.dto;

import com.sina.store.model.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class SavePaymentDTO {

    private List<Product> productList;
    private String customerId;

}
