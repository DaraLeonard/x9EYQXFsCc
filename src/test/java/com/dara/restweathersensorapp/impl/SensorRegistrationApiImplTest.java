package com.dara.restweathersensorapp.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class SensorRegistrationApiImplTest {

    @Nested
    @DisplayName("addSensor")
    class AddSensorTest {
        @Test
        public void GIVEN_callWithValidData_WHEN_addSensorCalled_THEN_registrationSuccessful() throws Exception {

        }

        @Test
        public void GIVEN_callWithDuplicatedData_WHEN_addSensorCalled_THEN_exceptionThrown() throws Exception {

        }

        @Test
        public void GIVEN_callWithInvalidData_WHEN_addSensorCalled_THEN_exceptionThrown() throws Exception {

        }
    }

    @Nested
    @DisplayName("replaceSensor")
    class ReplacementSensorEndpointTest {
        @Test
        public void GIVEN_callWithValidData_WHEN_replaceSensorCalled_THEN_sensorReplacementSuccessful() throws Exception {

        }

        @Test
        public void GIVEN_attemptToReplaceNonExistentSensor_WHEN_replaceSensorCalled_THEN_exceptionThrown() throws Exception {

        }

        @Test
        public void GIVEN_callWithInvalidData_WHEN_replaceSensorCalled_THEN_exceptionThrown() throws Exception {

        }
    }

    @Nested
    @DisplayName("deleteSensor")
    class DeleteSensorEndpointTest {
        @Test
        public void GIVEN_callWithValidData_WHEN_deleteSensorCalled_THEN_sensorDeleted() throws Exception {

        }

        @Test
        public void GIVEN_attemptToDeleteNonExistentSensor_WHEN_deleteSensorCalled_THEN_exceptionThrown() throws Exception {

        }
    }
}
