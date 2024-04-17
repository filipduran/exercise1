package com.example.exercise.controller;

import com.example.exercise.model.SensorReading;
import com.example.exercise.service.SensorReadingService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sensor-readings")
public class SensorReadingController {

    private final SensorReadingService sensorReadingService;

    @PostMapping
    public void registerSensorReading(@RequestBody SensorReading sensorReading) {
        sensorReadingService.registerSensorReading(sensorReading);
    }

    @GetMapping("/average")
    public double getAverageMetricValueForAllSensors(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @NotNull LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @NotNull LocalDateTime endDate) {
        return sensorReadingService.getAverageMetricValueForAllSensors(startDate, endDate);
    }

    @GetMapping("/average/{sensorId}")
    public double getAverageMetricValueForSensor(
            @PathVariable String sensorId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @NotNull LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) @NotNull LocalDateTime endDate) {
        return sensorReadingService.getAverageMetricValueForSensor(sensorId, startDate, endDate);
    }
}
