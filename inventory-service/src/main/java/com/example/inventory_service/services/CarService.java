package com.example.inventory_service.services;

import com.example.inventory_service.dto.CarRequest;
import com.example.inventory_service.dto.CarResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CarService {

    ResponseEntity<CarResponse> createCar(CarRequest carRequest);

    ResponseEntity<List<CarResponse>> getAllCars();

    ResponseEntity<String> buyCar(Long id, Long quantity);

    ResponseEntity<CarResponse> getCarById(Long id);
}
