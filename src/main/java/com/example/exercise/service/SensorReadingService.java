package com.example.exercise.service;

import com.example.exercise.model.SensorReading;

import java.time.LocalDateTime;

public interface SensorReadingService {

    void registerSensorReading(SensorReading sensorReading);
    double getAverageMetricValueForSensor(String sensorId, LocalDateTime startDate, LocalDateTime endDate);
    double getAverageMetricValueForAllSensors(LocalDateTime startDate, LocalDateTime endDate);
}
