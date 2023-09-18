package com.sina.store.exception;

public class NotEnoughProductStockException extends StoreException {

    public NotEnoughProductStockException(String productName) {
        super(IErrorCode.NOT_ENOUGH_PRODUCT_SCORE.getDisplayCode(), new String[]{productName});
    }

}
