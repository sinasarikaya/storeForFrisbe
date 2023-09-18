package com.sina.store.model.dto;

import lombok.Data;

@Data
public class RegisterCustomerDTO {

    private String name;
    private String surname;
    private String mobileNo;
    private String mailAddress;
    private String address;
    private String creditCardNumber;

}
