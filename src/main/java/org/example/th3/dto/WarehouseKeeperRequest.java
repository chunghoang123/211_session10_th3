package org.example.th3.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class WarehouseKeeperRequest {

    @NotBlank
    private String fullName;

    @NotBlank
    private String staffCode;
}