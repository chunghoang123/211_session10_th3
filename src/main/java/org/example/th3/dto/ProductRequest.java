package org.example.th3.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank
    private String name;

    @Min(0)
    private Long quantity;

    @NotBlank
    private String sku;
}