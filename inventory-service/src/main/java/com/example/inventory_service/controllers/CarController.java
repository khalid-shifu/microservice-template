package com.example.inventory_service.controllers;

import com.example.inventory_service.dto.CarRequest;
import com.example.inventory_service.dto.CarResponse;
import com.example.inventory_service.services.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
@Tag(name = "Car API", description = "API for managing Cars")
public class CarController {

    private final CarService carService;
    private final MessageSource messageSource;

    @GetMapping
    @Operation(summary = "Welcomes user to the system", description = "This endpoint returns a welcome message to the user.")
    public ResponseEntity<String> welcomeMessage() {
        Locale locale = LocaleContextHolder.getLocale();
        String welcomeMessage = messageSource.getMessage("welcome.message", null, "Default message", locale);
        return ResponseEntity.ok(welcomeMessage);
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new car", description = "This endpoint creates a new car with the provided details.")
    public ResponseEntity<CarResponse> createCar(@Valid @RequestBody CarRequest carRequest) {
        return carService.createCar(carRequest);
    }

    @GetMapping("/all-cars")
    @Operation(summary = "Fetch all cars", description = "This endpoint retrieves a list of all available cars.")
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

