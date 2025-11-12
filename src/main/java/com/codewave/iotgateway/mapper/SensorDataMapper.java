package com.codewave.iotgateway.mapper;

import com.codewave.iotgateway.dto.SensorDataDTO;
import com.codewave.iotgateway.model.SensorData;

public class SensorDataMapper {
    public static SensorDataDTO toDTO(SensorData s) {
        if (s == null) return null;
        SensorDataDTO dto = new SensorDataDTO();
        dto.setId(s.getId());
        dto.setSensorId(s.getSensorId());
        dto.setType(s.getType());
        dto.setValue(s.getValue());
        dto.setTimestamp(s.getTimestamp());
        return dto;
    }

    public static SensorData toEntity(SensorDataDTO dto) {
        if (dto == null) return null;
        SensorData s = new SensorData();
        s.setSensorId(dto.getSensorId());
        s.setType(dto.getType());
        s.setValue(dto.getValue());
        s.setTimestamp(dto.getTimestamp());
        return s;
    }
}
