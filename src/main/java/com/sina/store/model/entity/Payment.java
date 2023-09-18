package com.sina.store.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sina.store.model.base.ExtendedModel;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Payment extends ExtendedModel {

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "payment")
    private List<PaymentProduct> paymentProductList = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Customer customer;
    private double totalPrice;
    private OffsetDateTime dateOrder;
    private String deliveryAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Payment payment = (Payment) o;
        return getId() != null && Objects.equals(getId(), payment.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
