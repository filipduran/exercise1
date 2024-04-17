package com.example.exercise.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class SensorReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "sensorId is required")
    private String sensorId;
    private LocalDateTime timestamp;
    private double temperature;

    public SensorReading(Long id, String sensorId, LocalDateTime timestamp, double temperature) {
        this.id = id;
        this.sensorId = sensorId;
        this.timestamp = timestamp;
        this.temperature = temperature;
    }

    public SensorReading() {

    }
}
