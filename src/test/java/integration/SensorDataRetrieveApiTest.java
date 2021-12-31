package integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class SensorDataRetrieveApiTest {

    @Nested
    @DisplayName("/sensors")
    class GetAllSensorsEndpointTest {
        @Test
        public void GIVEN_getRequest_WHEN_queryAttempted_THEN_allRegisteredSensorsReturned() throws Exception {

        }
    }

    @Nested
    @DisplayName("/sensor/{sensorId}")
    class GetSpecificSensorEndpointTest {
        @Test
        public void GIVEN_requestForValidSensor_WHEN_queryAttempted_THEN_correctSensorReturned() throws Exception {

        }

        @Test
        public void GIVEN_requestForNonExistentSensor_WHEN_queryAttempted_THEN_responseDetailsThatSensorDoesNotExist() throws Exception {

        }
    }

    @Nested
    @DisplayName("/sensor/{sensorIds}/{dateRange}/{weatherAttribute}")
    class GetSensorDataEndpointTest {

        @Test
        public void GIVEN_requestForAllSensors_WHEN_queryAttempted_THEN_dataForAllSensorsReturned() throws Exception {

        }

        @Test
        public void GIVEN_requestForMultipleSensors_WHEN_queryAttempted_THEN_dataForSpecifiedSensorsReturned() throws Exception {

        }

        @Test
        public void GIVEN_requestForSingleSensor_WHEN_queryAttempted_THEN_dataForSensorReturned() throws Exception {

        }

        @Test
        public void GIVEN_requestForNonExistentSensor_WHEN_queryAttempted_THEN_responseDetailsThatSensorDoesNotExist() throws Exception {

        }

        @Test
        public void GIVEN_requestWithMissingDateRange_WHEN_queryAttempted_THEN_onlyLatestDataPointReturned() throws Exception {

        }

        @Test
        public void GIVEN_requestWithDateRange_WHEN_queryAttempted_THEN_averageDataForRangeReturned() throws Exception {

        }

        @Test
        public void GIVEN_requestWithSpecificWeatherAttribute_WHEN_queryAttempted_THEN_onlyRequestedWeatherMetricsReturned() throws Exception {

        }

        @Test
        public void GIVEN_requestWithNoWeatherAttribute_WHEN_queryAttempted_THEN_allWeatherMetricsReturned() throws Exception {

        }
    }
}
