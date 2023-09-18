package com.sina.store.exception;

public class CustomerNotFoundException extends StoreException {

    public CustomerNotFoundException(String field) {
        super(IErrorCode.CUSTOMER_NOT_FOUND.getDisplayCode(), new String[]{field});
    }
}
