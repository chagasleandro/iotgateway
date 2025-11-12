package com.codewave.iotgateway.repository;

import com.codewave.iotgateway.model.SensorData;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface SensorDataRepository extends MongoRepository<SensorData, String> {
    List<SensorData> findBySensorIdOrderByTimestampDesc(String sensorId);
}
