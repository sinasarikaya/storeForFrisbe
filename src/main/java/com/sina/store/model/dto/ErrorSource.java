package com.sina.store.model.dto;

import lombok.Data;

@Data
public class ErrorSource {
    private int code;
    private String message;
    private String description;
    private String[] fields;
}
