# exercise1

## Endpoints

### Register Sensor Reading

- **URL:** `/api/sensor-readings`
- **Method:** POST
- **Description:** Register a new sensor reading.
- **Request Body:**
    - `sensorId` (String, required): Unique identifier of the sensor.
    - `timestamp` (String, required): Timestamp of the sensor reading in ISO 8601 format (e.g., `2024-04-17T12:00:00`).
    - `temperature` (double, required): Temperature value in Celsius.

### Get Average Metric Value for All Sensors

- **URL:** `/api/sensor-readings/average`
- **Method:** GET
- **Description:** Get the average metric value (e.g., temperature) for all sensors within a specific date range.
- **Request Parameters:**
    - `startDate` (String, required): Start date of the date range in ISO 8601 format.
    - `endDate` (String, required): End date of the date range in ISO 8601 format.

### Get Average Metric Value for a Specific Sensor

- **URL:** `/api/sensor-readings/average/{sensorId}`
- **Method:** GET
- **Description:** Get the average metric value (e.g., temperature) for a specific sensor within a specific date range.
- **Request Parameters:**
    - `sensorId` (String, required): Unique identifier of the sensor.
    - `startDate` (String, required): Start date of the date range in ISO 8601 format.
    - `endDate` (String, required): End date of the date range in ISO 8601 format.