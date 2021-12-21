package com.dara.restweathersensorapp.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class SensorDataRetrieveApiImplTest {

    @Nested
    @DisplayName("getAllSensors")
    class GetAllSensorsTest {
        @Test
        public void GIVEN_callToGetAllSensors_WHEN_callExecuted_THEN_allRegisteredSensorsReturned() throws Exception {

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
