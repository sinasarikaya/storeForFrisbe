package com.sina.store.model.entity;

import com.sina.store.model.base.ExtendedModel;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class PaymentProduct extends ExtendedModel {

    private Integer productCount;

    @ManyToOne(fetch = FetchType.LAZY)
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private BigDecimal price;

}
