package com.codewave.iotgateway.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "sensorData")
public class SensorData {
    @Id
    private String id;
    private String sensorId;
    private String type;
    private Double value;
    private Long timestamp;
}
