package com.dara.restweathersensorapp.impl;

import com.dara.restweathersensorapp.SensorRepository;
import com.dara.restweathersensorapp.api.SensorDataAddApi;
import com.dara.restweathersensorapp.data.Sensor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
        public void GIVEN_addNewWeatherDataAttributeToExistentSensor_WHEN_methodCalled_THEN_dataIsAddedCorrectly() throws Exception {

        }

        @Test
        public void GIVEN_attemptToAddDataToNonExistentSensor_WHEN_methodCalled_THEN_requestFails() throws Exception {

        }
    }
}
