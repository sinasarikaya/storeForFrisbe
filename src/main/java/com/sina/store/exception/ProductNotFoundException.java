package com.sina.store.exception;

public class ProductNotFoundException extends StoreException {

    public ProductNotFoundException(String field) {
        super(IErrorCode.PRODUCT_NOT_FOUND.getDisplayCode(), new String[]{field});
    }

}
