package com.sina.store.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IErrorCode {

    PRODUCT_NOT_FOUND(1001),
    CUSTOMER_NOT_FOUND(1002),
    EXISTING_MOBILE_NO(1003),
    EXISTING_MAIL_ADDRESS(1004),
    CAN_NOT_BE_EMPTY(1005),
    WRONG_MAIL_ADDRESS_FORM(1006),
    EXISTING_CREDIT_CARD_NUMBER(1007),
    WRONG_CREDIT_CARD_NUMBER(1008),
    INVALID_PAYMENT_AMOUNT_EXCEPTION(1009),
    NOT_ENOUGH_PRODUCT_SCORE(1010);


    private final int displayCode;
}
