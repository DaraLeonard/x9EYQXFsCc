package com.dara.restweathersensorapp.impl;

import com.dara.restweathersensorapp.SensorRepository;
import com.dara.restweathersensorapp.api.SensorDataRetrieveApi;
import com.dara.restweathersensorapp.data.Sensor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class SensorDataRetrieveApiImplTest {

    @Autowired
    SensorRepository sensorRepository;

    @BeforeEach
    void setup() {
        final Sensor.SensorBuilder sb = new Sensor.SensorBuilder();
        final Sensor sensorA = sb.sensorId(1L).country("IE").city("Mayo").weatherMetricName("humidity").timeOfReading(LocalDateTime.MIN).valueOfReading(90D).build();
        final Sensor sensorB = sb.sensorId(2L).country("IE").city("Galway").weatherMetricName("temperature").timeOfReading(LocalDateTime.MIN).valueOfReading(33.1).build();
        final Sensor sensorC = sb.sensorId(3L).country("IE").city("Cork").weatherMetricName("humidity").timeOfReading(LocalDateTime.MIN).valueOfReading(60D).build();
        final Sensor sensorD = sb.sensorId(4L).country("IE").city("Dublin").weatherMetricName("wind-speed").timeOfReading(LocalDateTime.MIN).valueOfReading(33.1).build();

        sensorRepository.save(sensorA);
        sensorRepository.save(sensorB);
        sensorRepository.save(sensorC);
        sensorRepository.save(sensorD);
    }

    @Nested
    @DisplayName("getAllSensors")
    class GetAllSensorsTest {
        @Test
        public void GIVEN_callToGetAllSensors_WHEN_callExecuted_THEN_allRegisteredSensorsReturned() throws Exception {
            SensorDataRetrieveApi sensorDataRetrieveApi = new SensorDataRetrieveApiImpl(sensorRepository);

            assertEquals(4, sensorDataRetrieveApi.getAllSensors().getContent().size());
        }
    }

    @Nested
    @DisplayName("getSensorById")
    class GetSpecificSensorTest {
        @Test
        public void GIVEN_callForValidSensor_WHEN_callExecuted_THEN_correctSensorReturned() throws Exception {

        }

        @Test
        public void GIVEN_callForNonExistentSensor_WHEN_callExecuted_THEN_exceptionThrown() throws Exception {

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
