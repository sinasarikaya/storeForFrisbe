package com.sina.store.model.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class ResponseData<T> {

    private T data;
    private List<ErrorSource> errors;
    private Map<String, String> metaDatum;

    private ResponseData(Builder<T> builder) {
        data = builder.data;
        errors = builder.errors;
        metaDatum = builder.metaDatum;
    }

    public static class Builder<T> {

        private T data;
        private List<ErrorSource> errors;
        private Map<String, String> metaDatum;


        public Builder data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> dataWithType(T data) {
            this.data = data;
            return this;
        }


        public Builder errors(List<ErrorSource> errors) {
            this.errors = errors;
            return this;
        }

        public Builder errors(ErrorSource error) {
            if (this.errors == null) {
                this.errors = new ArrayList<>();
            }
            this.errors.add(error);
            return this;
        }

        public Builder putMetaData(String key, String value) {
            if (this.metaDatum == null) {
                this.metaDatum = new HashMap<>();
            }

            this.metaDatum.put(key, value);

            return this;
        }

        public ResponseData build() {
            return new ResponseData(this);
        }


    }
}
