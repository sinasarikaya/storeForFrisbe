package com.sina.store.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class StoreBulkException extends RuntimeException {

    private List<StoreException> exceptions = new ArrayList<>();

    public void throwIfNecessary() {
        if (!exceptions.isEmpty()) {
            throw this;
        }
    }

    public void addException(StoreException aesException) {
        this.exceptions.add(aesException);
    }
}
