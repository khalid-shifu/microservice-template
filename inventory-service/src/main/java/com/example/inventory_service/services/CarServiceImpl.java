package com.example.inventory_service.services;

import com.example.inventory_service.dto.CarRequest;
import com.example.inventory_service.dto.CarResponse;
import com.example.inventory_service.entity.CarEntity;
import com.example.inventory_service.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public ResponseEntity<CarResponse> createCar(CarRequest carRequest) {
        CarEntity carEntity = CarEntity.builder()
                .modelName(carRequest.getModelName())
                .color(carRequest.getColor())
                .quantity(carRequest.getQuantity())
                .build();
        CarEntity savedCar = carRepository.save(carEntity);
        CarResponse response = new ModelMapper().map(savedCar, CarResponse.class);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .replacePath("/inventory-service/car/{id}")
                .host("localhost")
                .port(9090)
                .buildAndExpand(savedCar.getId())
                .toUri();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity<>(response, headers, HttpStatus.CREATED);
        //return ResponseEntity.created(location).body(response);
    }

    @Override
    public ResponseEntity<List<CarResponse>> getAllCars() {
        List<CarEntity> carEntities = carRepository.findAll();
        List<CarResponse> carResponseList = carEntities.stream()
                .map(carEntity -> new ModelMapper().map(carEntity, CarResponse.class))
                .toList();

        return new ResponseEntity<>(carResponseList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> buyCar(Long id, Long quantity) {
        Optional<CarEntity> carOptional = carRepository.findById(id);

        if (carOptional.isEmpty()) {
            return new ResponseEntity<>("Wrong car ID!", HttpStatus.BAD_REQUEST);
        }

        CarEntity car = carOptional.get();
        long availableQuantity = car.getQuantity();

        if (availableQuantity >= quantity) {
            availableQuantity = availableQuantity - quantity;
            car.setQuantity(availableQuantity);
            carRepository.save(car);
            return new ResponseEntity<>("Car purchased successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Not enough quantity available!", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<CarResponse> getCarById(Long id) {
        Optional<CarEntity> carOptional = carRepository.findById(id);

        if (carOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        CarEntity car = carOptional.get();
        CarResponse response = new ModelMapper().map(car, CarResponse.class);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
