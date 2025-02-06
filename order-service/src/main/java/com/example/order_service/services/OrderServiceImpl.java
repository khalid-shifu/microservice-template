package com.example.order_service.services;

import com.example.order_service.feignConfig.OrderServiceFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderServiceFeignClient orderServiceFeignClient;

    @Override
    public ResponseEntity<String> buyCar(Long id, Long quantity) {
        ResponseEntity<String> response;
        try {
            response = orderServiceFeignClient.buyCar(id, quantity);
            return response;
        } catch (FeignException e) {
            HttpStatus status = HttpStatus.resolve(e.status());

            if (status == null) {
                status = HttpStatus.INTERNAL_SERVER_ERROR;
                return new ResponseEntity<>("inventory-service down!", status);
            }
            System.out.println("status = " + status);
            return new ResponseEntity<>(e.contentUTF8(), status);
        }
    }
}
