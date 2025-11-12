package com.codewave.iotgateway.service;

import com.codewave.iotgateway.model.SensorData;
import com.codewave.iotgateway.repository.SensorDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.data.domain.Page;

public class SensorServiceTest {

    private SensorDataRepository repository;
    private SensorService service;

    @BeforeEach
    public void setup() {
        repository = Mockito.mock(SensorDataRepository.class);
        service = new SensorService(repository);
    }

    @Test
    public void testFindById_notFound() {
        Mockito.when(repository.findById("nope")).thenReturn(Optional.empty());
        assertNull(service.findById("nope"));
    }

    @Test
    public void testFindAll_pagination() {
        SensorData s1 = new SensorData(); s1.setId("1"); s1.setSensorId("esp-1"); s1.setType("temp"); s1.setValue(10.0); s1.setTimestamp(1000L);
        SensorData s2 = new SensorData(); s2.setId("2"); s2.setSensorId("esp-2"); s2.setType("temp"); s2.setValue(20.0); s2.setTimestamp(2000L);
        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(s1, s2));
        Page<?> page = service.findAll(null, null, null, null, 0, 1);
        assertEquals(1, page.getNumberOfElements());
        assertEquals(2, page.getTotalElements());
    }
}
