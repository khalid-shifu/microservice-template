package com.example.order_service.feignConfig;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inventory-service")
public interface OrderServiceFeignClient {

    @GetMapping("/car/buy/{id}/quantity/{quantity}")
    public ResponseEntity<String> buyCar(@PathVariable("id") Long id, @PathVariable("quantity") Long quantity);
}
