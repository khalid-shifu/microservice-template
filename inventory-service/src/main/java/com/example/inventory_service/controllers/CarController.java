package com.example.inventory_service.controllers;

import com.example.inventory_service.dto.CarRequest;
import com.example.inventory_service.dto.CarResponse;
import com.example.inventory_service.services.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService carService;

    @PostMapping("/create")
    public ResponseEntity<CarResponse> createCar(@Valid @RequestBody CarRequest carRequest) {
        return carService.createCar(carRequest);
    }

    @GetMapping("/all-cars")
    public ResponseEntity<List<CarResponse>> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/buy/{id}/quantity/{quantity}")
    public ResponseEntity<String> buyCar(@PathVariable("id") Long id, @PathVariable("quantity") Long quantity) {
        return carService.buyCar(id, quantity);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable("id") Long id) {
        return carService.getCarById(id);
    }
}

