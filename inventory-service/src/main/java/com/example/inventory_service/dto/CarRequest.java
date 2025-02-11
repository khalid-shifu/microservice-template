package com.example.inventory_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CarRequest {
    @NotEmpty(message = "Model name is required")
    @NotNull(message = "Model name cannot be null")
    private String modelName;

    @Size(min = 3, max = 10, message = "Color should be between 3 and 10 characters")
    private String color;

    @Min(value = 1, message = "Quantity should be greater than 0")
    private Long quantity;
}
