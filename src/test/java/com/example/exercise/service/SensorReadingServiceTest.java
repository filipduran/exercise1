package com.example.exercise.service;

import com.example.exercise.model.SensorReading;
import com.example.exercise.repository.SensorReadingRepository;
import com.example.exercise.service.implementation.SensorReadingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SensorReadingServiceTest {

    @Mock
    private SensorReadingRepository sensorReadingRepository;

    @InjectMocks
    private SensorReadingServiceImpl sensorReadingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAverageMetricValueForSensor() {
        String sensorId = "sensor1";
        LocalDateTime startDate = LocalDateTime.of(2024, 4, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 4, 10, 0, 0);

        List<SensorReading> sensorReadings = new ArrayList<>();
        sensorReadings.add(new SensorReading(1L, "sensor1", LocalDateTime.of(2024, 4, 1, 12, 0), 20.0));
        sensorReadings.add(new SensorReading(2L, "sensor1", LocalDateTime.of(2024, 4, 2, 12, 0), 22.0));

        when(sensorReadingRepository.findBySensorIdAndTimestampBetween(sensorId, startDate, endDate)).thenReturn(sensorReadings);

        double expectedAverage = (20.0 + 22.0) / 2;
        double actualAverage = sensorReadingService.getAverageMetricValueForSensor(sensorId, startDate, endDate);

        assertEquals(expectedAverage, actualAverage);
    }

    @Test
    void testGetAverageMetricValueForAllSensors() {
        LocalDateTime startDate = LocalDateTime.of(2024, 4, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 4, 10, 0, 0);

        List<SensorReading> sensorReadings = new ArrayList<>();
        sensorReadings.add(new SensorReading(1L, "sensor1", LocalDateTime.of(2024, 4, 1, 12, 0), 20.0));
        sensorReadings.add(new SensorReading(2L, "sensor1", LocalDateTime.of(2024, 4, 2, 12, 0), 22.0));
        sensorReadings.add(new SensorReading(3L, "sensor2", LocalDateTime.of(2024, 4, 3, 12, 0), 24.0));

        when(sensorReadingRepository.findByTimestampBetween(startDate, endDate)).thenReturn(sensorReadings);

        double expectedAverage = (20.0 + 22.0 + 24) / 3;
        double actualAverage = sensorReadingService.getAverageMetricValueForAllSensors(startDate, endDate);

        assertEquals(expectedAverage, actualAverage);
    }
}
