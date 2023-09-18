package com.sina.store.exception;

public class ExistingCreditCardNumber extends StoreException {
    public ExistingCreditCardNumber(String field) {
        super(IErrorCode.EXISTING_CREDIT_CARD_NUMBER.getDisplayCode(), new String[]{field});
    }
}
