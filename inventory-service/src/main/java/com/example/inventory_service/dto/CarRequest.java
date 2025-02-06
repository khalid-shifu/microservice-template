package com.example.inventory_service.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CarRequest {

    private Long id;
    private String modelName;
    private String color;
    private Long quantity;
}
