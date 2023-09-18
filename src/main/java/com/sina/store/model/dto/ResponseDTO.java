package com.sina.store.model.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseDTO {

    public static final String SUCCESS = "SUCCESS";
    public static final String ERROR = "ERROR";

}
