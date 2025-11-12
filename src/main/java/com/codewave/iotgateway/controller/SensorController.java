package com.codewave.iotgateway.controller;

import com.codewave.iotgateway.dto.SensorDataDTO;
import com.codewave.iotgateway.service.SensorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {
    private final SensorService service;

    public SensorController(SensorService service) {
        this.service = service;
    }

    @GetMapping
    public Page<SensorDataDTO> all(
            @RequestParam(value = "sensorId", required = false) String sensorId,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "from", required = false) Long from,
            @RequestParam(value = "to", required = false) Long to,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "20") int size
    ) {
        return service.findAll(sensorId, type, from, to, page, size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorDataDTO> getById(@PathVariable String id) {
        SensorDataDTO dto = service.findById(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<SensorDataDTO> create(@Valid @RequestBody SensorDataDTO dto) {
        SensorDataDTO created = service.save(dto);
        return ResponseEntity.ok(created);
    }
}
