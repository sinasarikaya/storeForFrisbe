package com.sina.store.exception;

public class ExistingMailAddress extends StoreException{

    public ExistingMailAddress(String field) {
        super(IErrorCode.EXISTING_MAIL_ADDRESS.getDisplayCode(), new String[]{field});
    }
}
