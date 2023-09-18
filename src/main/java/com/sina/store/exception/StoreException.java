package com.sina.store.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Getter
@Setter
public class StoreException extends RuntimeException {

    private int code;
    private String message;
    private String description;
    private String[] fields;
    private String[] placeHolders;
    private HttpStatus httpStatus;
    private String detailMessage;

    public StoreException(int code, String message, String description, String[] fields, String[] placeHolders, HttpStatus httpStatus, Throwable t) {
        super(message, t);
        this.code = code;
        this.message = message;
        this.description = description;
        this.fields = fields;
        this.placeHolders = placeHolders;
        this.httpStatus = httpStatus;
    }

    public StoreException(int code, String[] fields, String[] placeHolders) {
        this(code, null, null, fields, placeHolders, HttpStatus.UNPROCESSABLE_ENTITY, null);
    }

    public StoreException(int code, String[] fields, String[] placeHolders, Throwable t) {
        this(code, null, null, fields, placeHolders, HttpStatus.UNPROCESSABLE_ENTITY, t);
    }

    public StoreException(int code) {
        this(code, null, null, null, null, HttpStatus.UNPROCESSABLE_ENTITY, null);
    }

    public StoreException(int code, Throwable t) {
        this(code, null, null, null, null, HttpStatus.UNPROCESSABLE_ENTITY, t);
    }

    public StoreException(int code, HttpStatus httpStatus) {
        this(code, null, null, null, null, httpStatus, null);
    }

    public StoreException(int code, HttpStatus httpStatus, Throwable t) {
        this(code, null, null, null, null, httpStatus, t);
    }

    public StoreException(int code, String[] fields) {
        this(code, null, null, fields, null, HttpStatus.UNPROCESSABLE_ENTITY, null);
    }

    public StoreException(int code, String[] fields, Throwable t) {
        this(code, null, null, fields, null, HttpStatus.UNPROCESSABLE_ENTITY, t);
    }


}
