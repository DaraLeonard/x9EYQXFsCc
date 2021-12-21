package com.dara.restweathersensorapp.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class SensorDataAddApiImplTest {

    @Nested
    @DisplayName("addMetricValue")
    class AddMetricValueTest {
        @Test
        void GIVEN_addDataToExistentSensor_WHEN_methodCalled_THEN_dataIsAddedCorrectly() {
            SensorDataAddApiImpl sensorDataAddApi = new SensorDataAddApiImpl();
        }

        @Test
        public void GIVEN_addNewWeatherDataAttributeToExistentSensor_WHEN_methodCalled_THEN_dataIsAddedCorrectly() throws Exception {

        }

        @Test
        public void GIVEN_attemptToAddDataToNonExistentSensor_WHEN_methodCalled_THEN_requestFails() throws Exception {

        }
    }
}
