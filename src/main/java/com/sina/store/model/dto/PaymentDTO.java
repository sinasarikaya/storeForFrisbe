package com.sina.store.model.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PaymentDTO {
    private Long id;
    List<PaymentProductDTO> productDTOList;
    private Long customerId;
    private BigDecimal paymentAmount;
    private String dateOrder;
    private double totalPrice;
    private String deliveryAddress;
}
