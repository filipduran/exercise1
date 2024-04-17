package com.example.exercise.controller;

import com.example.exercise.service.SensorReadingService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SensorReadingControllerTest {

    @Mock
    private SensorReadingService sensorReadingService;

    @InjectMocks
    private SensorReadingController sensorReadingController;

    private MockMvc mockMvc;

    @Test
    void testRegisterSensorReading() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(sensorReadingController).build();

        String json = "{\"sensorId\":\"sensor1\",\"timestamp\":\"2024-04-17T12:00:00\",\"temperature\":20.0}";

        mockMvc.perform(post("/api/sensor-readings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(sensorReadingService, times(1)).registerSensorReading(any());
    }

    @Test
    void testGetAverageMetricValueForAllSensors() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(sensorReadingController).build();

        LocalDateTime startDate = LocalDateTime.of(2024, 4, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 4, 10, 0, 0);

        double expectedAverage = 20.0;

        when(sensorReadingService.getAverageMetricValueForAllSensors(startDate, endDate)).thenReturn(expectedAverage);

        mockMvc.perform(get("/api/sensor-readings/average")
                        .param("startDate", "2024-04-01T00:00:00")
                        .param("endDate", "2024-04-10T00:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expectedAverage)));
    }

    @Test
    void testGetAverageMetricValueForSensor() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(sensorReadingController).build();

        String sensorId = "sensor1";
        LocalDateTime startDate = LocalDateTime.of(2024, 4, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 4, 10, 0, 0);

        double expectedAverage = 20.0;

        when(sensorReadingService.getAverageMetricValueForSensor(sensorId, startDate, endDate)).thenReturn(expectedAverage);

        mockMvc.perform(get("/api/sensor-readings/average/{sensorId}", sensorId)
                        .param("startDate", "2024-04-01T00:00:00")
                        .param("endDate", "2024-04-10T00:00:00"))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(expectedAverage)));
    }
}
