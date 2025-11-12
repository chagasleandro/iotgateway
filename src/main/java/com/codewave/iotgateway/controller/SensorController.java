package com.codewave.iotgateway.controller;

import com.codewave.iotgateway.model.SensorData;
import com.codewave.iotgateway.repository.SensorDataRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {
    private final SensorDataRepository repository;

    public SensorController(SensorDataRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<SensorData> all(@RequestParam(value = "sensorId", required = false) String sensorId) {
        if (sensorId != null && !sensorId.isEmpty()) {
            return repository.findBySensorIdOrderByTimestampDesc(sensorId);
        }
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public SensorData getById(@PathVariable String id) {
        return repository.findById(id).orElse(null);
    }
}
