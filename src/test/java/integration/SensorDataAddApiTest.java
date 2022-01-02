package integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class SensorDataAddApiTest {


    @Nested
    @DisplayName("/addMetric/{sensorId}/{metricName}/{metricValue}")
    class GetAllSensorsEndpointTest {
        @Test
        public void GIVEN_attemptToAddDataToExistentSensor_WHEN_requestExecuted_THEN_dataIsAddedCorrectly() throws Exception {

        }

        @Test
        public void GIVEN_attemptToAddNewWeatherDataAttributeToExistentSensor_WHEN_requestExecuted_THEN_dataIsAddedCorrectly() throws Exception {

        }

        @Test
        public void GIVEN_attemptToAddDataToNonExistentSensor_WHEN_requestExecuted_THEN_requestFails() throws Exception {

        }
    }
}
