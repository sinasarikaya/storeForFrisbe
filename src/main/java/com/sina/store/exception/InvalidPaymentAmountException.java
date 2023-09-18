package com.sina.store.exception;

public class InvalidPaymentAmountException extends StoreException {
    public InvalidPaymentAmountException() {
        super(IErrorCode.INVALID_PAYMENT_AMOUNT_EXCEPTION.getDisplayCode());
    }
}
