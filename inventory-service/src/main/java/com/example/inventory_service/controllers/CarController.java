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
@RequestMapping("/v1/car")
@Tag(name = "Car API", description = "API for managing Cars")
public class CarController {

    private final CarService carService;
    private final MessageSource messageSource;

    @GetMapping
    @Operation(summary = "Welcome message", description = "Welcomes user to the system")
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

    // versioning with request parameter like Amazon
    // its request url is /v1/car/version?v=1
    @GetMapping(path = "/version", params = "v=1")
    public ResponseEntity<String> getStringForV1() {
        return ResponseEntity.ok("Version 1");
    }

    @GetMapping(path = "/version", params = "v=2")
    public ResponseEntity<String> getStringForV2() {
        return ResponseEntity.ok("Version 2");
    }

    // versioning with headers like Microsoft
    @GetMapping(path = "/version", headers = "X-API-VERSION=1")
    public ResponseEntity<String> getStringForV1Header() {
        return ResponseEntity.ok("Request header Version 1");
    }

    @GetMapping(path = "/version", headers = "X-API-VERSION=2")
    public ResponseEntity<String> getStringForV2Header() {
        return ResponseEntity.ok("Request header Version 2");
    }

    // versioning with media type like github
    // header -> Accept: application/vnd.company.app-v1+json
    @GetMapping(path = "/version", produces = "application/vnd.company.app-v1+json")
    public ResponseEntity<String> getStringForV1MediaType() {
        return ResponseEntity.ok("media type Version 1");
    }

    @GetMapping(path = "/version", produces = "application/vnd.company.app-v2+json")
    public ResponseEntity<String> getStringForV2MediaType() {
        return ResponseEntity.ok("media type Version 2");
    }
}
