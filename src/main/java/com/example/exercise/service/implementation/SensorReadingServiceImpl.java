package com.example.exercise.service.implementation;

import com.example.exercise.model.SensorReading;
import com.example.exercise.repository.SensorReadingRepository;
import com.example.exercise.service.SensorReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorReadingServiceImpl implements SensorReadingService {
    private final SensorReadingRepository sensorReadingRepository;

    public void registerSensorReading(SensorReading sensorReading) {
        sensorReadingRepository.save(sensorReading);
    }

    public double getAverageMetricValueForSensor(String sensorId, LocalDateTime startDate, LocalDateTime endDate) {
        List<SensorReading> readings = sensorReadingRepository.findBySensorIdAndTimestampBetween(sensorId, startDate, endDate);
        return calculateAverage(readings);
    }

    public double getAverageMetricValueForAllSensors(LocalDateTime startDate, LocalDateTime endDate) {
        List<SensorReading> readings = sensorReadingRepository.findByTimestampBetween(startDate, endDate);
        return calculateAverage(readings);
    }

    private double calculateAverage(List<SensorReading> readings) {
        return readings.stream()
                .mapToDouble(SensorReading::getTemperature)
                .average()
                .orElse(0.0);
    }
}
