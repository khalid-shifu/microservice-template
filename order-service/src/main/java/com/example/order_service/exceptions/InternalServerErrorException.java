package com.example.order_service.exceptions;

import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends RuntimeException {

    public InternalServerErrorException(String message) {
        super(message);
    }
}
