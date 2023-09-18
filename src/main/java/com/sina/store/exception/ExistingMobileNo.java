package com.sina.store.exception;

public class ExistingMobileNo extends StoreException {
    public ExistingMobileNo(String field) {
        super(IErrorCode.EXISTING_MOBILE_NO.getDisplayCode(), new String[]{field});
    }
}
