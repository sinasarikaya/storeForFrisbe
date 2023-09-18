package com.sina.store.model.entity;

import com.sina.store.model.base.ExtendedModel;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Customer extends ExtendedModel {

    private String name;
    private String surname;
    private String mobileNo;
    private String mailAddress;
    private String address;
    private String creditCardNumber;

}
