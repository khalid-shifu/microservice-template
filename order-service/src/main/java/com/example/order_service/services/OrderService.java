package com.example.order_service.services;

import org.springframework.http.ResponseEntity;


public interface OrderService {
    ResponseEntity<String> buyCar(Long id, Long quantity);
}
