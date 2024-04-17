package com.example.exercise.repository;

import com.example.exercise.model.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {
    List<SensorReading> findBySensorIdAndTimestampBetween(String sensorId, LocalDateTime startDate, LocalDateTime endDate);

    List<SensorReading> findByTimestampBetween(LocalDateTime startDate, LocalDateTime endDate);
}
