package com.dara.restweathersensorapp.impl;

import com.dara.restweathersensorapp.SensorRepository;
import com.dara.restweathersensorapp.api.SensorDataRetrieveApi;
import com.dara.restweathersensorapp.data.Sensor;
import com.dara.restweathersensorapp.exception.SensorNotFoundException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class SensorDataRetrieveApiImplTest {

    @Autowired
    SensorRepository sensorRepository;

    private SensorDataRetrieveApi sensorDataRetrieveApi;

    @BeforeEach
    void setup() {
        sensorDataRetrieveApi = new SensorDataRetrieveApiImpl(sensorRepository);

        final Sensor.SensorBuilder sb = new Sensor.SensorBuilder();
        Sensor sensorA = sb.sensorId(1L).country("IE").city("Mayo").weatherMetricName("humidity").timeOfReading(LocalDateTime.MIN).valueOfReading(90D).build();
        Sensor sensorB = sb.sensorId(2L).country("IE").city("Galway").weatherMetricName("temperature").timeOfReading(LocalDateTime.MIN).valueOfReading(33.1).build();
        Sensor sensorC = sb.sensorId(3L).country("IE").city("Cork").weatherMetricName("humidity").timeOfReading(LocalDateTime.MIN).valueOfReading(60D).build();
        Sensor sensorD = sb.sensorId(4L).country("IE").city("Dublin").weatherMetricName("wind-speed").timeOfReading(LocalDateTime.MIN).valueOfReading(33.1).build();

        sensorRepository.save(sensorA);
        sensorRepository.save(sensorB);
        sensorRepository.save(sensorC);
        sensorRepository.save(sensorD);
    }

    @Nested
    @DisplayName("getAllSensors")
    class GetAllSensorsTest {
        @Test
        public void GIVEN_callToGetAllSensors_WHEN_callExecuted_THEN_allRegisteredSensorsReturned() {
            assertEquals(4, sensorDataRetrieveApi.getAllSensors().getContent().size());
        }
    }

    @Nested
    @DisplayName("getSensorById")
    class GetSpecificSensorTest {
        @Test
        public void GIVEN_callForValidSensor_WHEN_callExecuted_THEN_correctSensorReturned() {
            final Sensor sensor = sensorDataRetrieveApi.getSensorById(1L).getContent();
            assertEquals(1L, sensor.getSensorId());
            assertEquals("IE", sensor.getCountry());
            assertEquals("Mayo", sensor.getCity());
            assertEquals(LocalDateTime.MIN, sensor.getWeatherData().get("humidity").getTimeOfReading());
            assertEquals(90D, sensor.getWeatherData().get("humidity").getWeatherData());
        }

        @Test
        public void GIVEN_callForNonExistentSensor_WHEN_callExecuted_THEN_exceptionThrown() {
            final SensorNotFoundException sensorNotFoundException = Assertions.assertThrows(SensorNotFoundException.class,
                    () -> sensorDataRetrieveApi.getSensorById(100L).getContent());

            assertEquals("Sensor with ID:100 could not be found", sensorNotFoundException.getMessage());
        }
    }

    @Nested
    @DisplayName("getSensorData")
    class GetSensorDataTest {

        @Test
        public void GIVEN_callForAllSensors_WHEN_callExecuted_THEN_allSensorsReturned() throws Exception {

        }

        @Test
        public void GIVEN_callForMultipleSensors_WHEN_callExecuted_THEN_specifiedSensorsReturned() throws Exception {

        }

        @Test
        public void GIVEN_callForSingleSensor_WHEN_callExecuted_THEN_correctSensorReturned() throws Exception {

        }

        @Test
        public void GIVEN_callForNonExistentSensor_WHEN_callExecuted_THEN_exceptionThrown() throws Exception {

        }

        @Test
        public void GIVEN_callWithMissingDateRange_WHEN_callExecuted_THEN_lastDataReturned() throws Exception {

        }

        @Test
        public void GIVEN_callWithDateRange_WHEN_callExecuted_THEN_averageDataForRangeReturned() throws Exception {

        }

        @Test
        public void GIVEN_callWithSpecificWeatherAttribute_WHEN_callExecuted_THEN_requestedWeatherMetricsReturned() throws Exception {

        }

        @Test
        public void GIVEN_callWithNoWeatherAttribute_WHEN_callExecuted_THEN_allWeatherMetricsReturned() throws Exception {

        }
    }
}
