package com.example.order_service.exceptions;

import feign.FeignException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FeignClientException extends RuntimeException {
    private final HttpStatus status;
    private final String responseBody;

    public FeignClientException(String message, HttpStatus status, String responseBody) {
        super(message);
        this.status = status;
        this.responseBody = responseBody;
    }

}