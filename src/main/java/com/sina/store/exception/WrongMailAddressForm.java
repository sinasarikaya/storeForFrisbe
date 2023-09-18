package com.sina.store.exception;

public class WrongMailAddressForm extends StoreException{

    public WrongMailAddressForm() {
        super(IErrorCode.WRONG_MAIL_ADDRESS_FORM.getDisplayCode());
    }

}
