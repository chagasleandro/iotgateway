package com.codewave.iotgateway.service;

import com.codewave.iotgateway.dto.SensorDataDTO;
import com.codewave.iotgateway.mapper.SensorDataMapper;
import com.codewave.iotgateway.model.SensorData;
import com.codewave.iotgateway.repository.SensorDataRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SensorService {
    private final SensorDataRepository repository;

    public SensorService(SensorDataRepository repository) {
        this.repository = repository;
    }

    public Page<SensorDataDTO> findAll(String sensorId, String type, Long from, Long to, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<SensorData> list = repository.findAll(); // simple approach for demo
        // apply filters
        List<SensorData> filtered = list.stream().filter(s -> {
            if (sensorId != null && !sensorId.isEmpty() && !s.getSensorId().equals(sensorId)) return false;
            if (type != null && !type.isEmpty() && !s.getType().equals(type)) return false;
            if (from != null && s.getTimestamp() < from) return false;
            if (to != null && s.getTimestamp() > to) return false;
            return true;
        }).collect(Collectors.toList());
        int start = Math.min((int)pageable.getOffset(), filtered.size());
        int end = Math.min((start + pageable.getPageSize()), filtered.size());
        List<SensorDataDTO> dtoList = filtered.subList(start, end).stream().map(SensorDataMapper::toDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, filtered.size());
    }

    public SensorDataDTO findById(String id) {
        return SensorDataMapper.toDTO(repository.findById(id).orElse(null));
    }

    public SensorDataDTO save(SensorDataDTO dto) {
        SensorData s = SensorDataMapper.toEntity(dto);
        SensorData saved = repository.save(s);
        return SensorDataMapper.toDTO(saved);
    }
}
