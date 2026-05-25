package org.example.th3.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExportRequest {

    @NotBlank
    private String sku;

    @NotNull
    @Min(1)
    private Long quantity;

    @NotNull
    private Long keeperId;
}