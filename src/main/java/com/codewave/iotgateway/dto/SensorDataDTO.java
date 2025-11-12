package com.codewave.iotgateway.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class SensorDataDTO {
    private String id;

    @NotBlank
    private String sensorId;

    @NotBlank
    private String type;

    @NotNull
    @PositiveOrZero
    private Double value;

    @NotNull
    @PositiveOrZero
    private Long timestamp;
}
