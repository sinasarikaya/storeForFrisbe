package com.sina.store.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentProductDTO {

    private Long id;

    private Integer productCount;

    private Long paymentId;

    private ProductDTO productDTO;

}


