package com.example.order_service.controllers;

import com.example.order_service.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}/quantity/{quantity}")
    public ResponseEntity<String> orderCar(@PathVariable("id") Long id, @PathVariable("quantity") Long quantity) {
        return orderService.buyCar(id, quantity);
    }
}
