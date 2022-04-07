package com.mirandasidney.pdv.api.handler;

import com.mirandasidney.pdv.api.exception.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {

    public static ResponseEntity<Object> build(ApiError apiError) {
        return new ResponseEntity<>(apiError, HttpStatus.valueOf(apiError.getStatus()));
    }
}
