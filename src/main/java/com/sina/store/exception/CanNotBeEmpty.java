package com.sina.store.exception;

public class CanNotBeEmpty extends StoreException{

    public CanNotBeEmpty(String field) {
        super(IErrorCode.CAN_NOT_BE_EMPTY.getDisplayCode(), new String[]{field});
    }
}
