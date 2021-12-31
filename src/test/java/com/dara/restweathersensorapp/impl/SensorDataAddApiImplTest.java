package com.dara.restweathersensorapp.impl;

import com.dara.restweathersensorapp.SensorRepository;
import com.dara.restweathersensorapp.api.SensorDataAddApi;
import com.dara.restweathersensorapp.data.Sensor;
import com.dara.restweathersensorapp.data.WeatherData;
import com.dara.restweathersensorapp.exception.SensorNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class SensorDataAddApiImplTest {

    @Autowired
    SensorRepository sensorRepository;

    private SensorDataAddApi sensorDataAddApi;

    private final Sensor.SensorBuilder sb = new Sensor.SensorBuilder();

    @BeforeEach
    void setup() {
        sensorDataAddApi = new SensorDataAddApiImpl(sensorRepository);

        final Sensor sensor = sb.sensorId(1L).country("IE").city("Mayo").build();
        sensorRepository.save(sensor);
    }

    @Nested
    @DisplayName("addMetricValue")
    class AddMetricValueTest {
        @Test
        void GIVEN_addDataToExistentSensor_WHEN_methodCalled_THEN_dataIsAddedCorrectly() {
            final Sensor sensor = sb.sensorId(1L).country("IE").city("Mayo").build();
            sensorRepository.save(sensor);

            sensorDataAddApi.addMetricValue(1L, "humidity", 40D);

            final Sensor retrievedSensor = sensorRepository.findById(1L).get();
            assertEquals(1, retrievedSensor.getWeatherData().size());
            assertEquals("humidity", retrievedSensor.getWeatherData().get(0).getWeatherMetricName());
            assertEquals(40D, retrievedSensor.getWeatherData().get(0).getWeatherMetricValue());
        }

        @Test
        public void GIVEN_addNewWeatherDataAttributeToExistentSensor_WHEN_methodCalled_THEN_dataIsAddedCorrectly() {
            final Sensor sensor =
                    sb.sensorId(1L).country("IE").city("Mayo").weatherData(new WeatherData(LocalDateTime.MIN, "humidity", 90D)).build();
            sensorRepository.save(sensor);

            sensorDataAddApi.addMetricValue(1L, "wind-speed", 55.5);

            final Sensor retrievedSensor = sensorRepository.findById(1L).get();
            assertEquals(2, retrievedSensor.getWeatherData().size());
            assertEquals("humidity", retrievedSensor.getWeatherData().get(0).getWeatherMetricName());
            assertEquals(90D, retrievedSensor.getWeatherData().get(0).getWeatherMetricValue());

            assertEquals("wind-speed", retrievedSensor.getWeatherData().get(1).getWeatherMetricName());
            assertEquals(55.5, retrievedSensor.getWeatherData().get(1).getWeatherMetricValue());
        }

        @Test
        public void GIVEN_attemptToAddDataToNonExistentSensor_WHEN_methodCalled_THEN_requestFails() {
            final Sensor sensor = sb.sensorId(1L).country("IE").city("Mayo").build();
            sensorRepository.save(sensor);

            final SensorNotFoundException sensorNotFoundException = Assertions.assertThrows(SensorNotFoundException.class,
                    () -> sensorDataAddApi.addMetricValue(2L, "humidity", 40D));
            assertEquals("Sensor with ID:2 could not be found", sensorNotFoundException.getMessage());
        }
    }
}
