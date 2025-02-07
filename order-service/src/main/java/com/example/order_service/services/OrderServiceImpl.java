package com.example.order_service.services;

import com.example.order_service.exceptions.FeignClientException;
import com.example.order_service.exceptions.ServiceUnavailableException;
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
                    throw new ServiceUnavailableException("inventory-service down!");
                }
                System.out.println("status : " + status);
                System.out.println("e.contentUTF8() : " + e.contentUTF8());
                throw new FeignClientException("Feign client exception", status, e.contentUTF8());
        }
    }
}
