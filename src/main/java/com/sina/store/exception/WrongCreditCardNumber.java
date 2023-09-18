package com.sina.store.exception;

public class WrongCreditCardNumber extends StoreException {
    public WrongCreditCardNumber(String field) {
        super(IErrorCode.WRONG_CREDIT_CARD_NUMBER.getDisplayCode(), new String[]{field});
    }
}
