package integration;

import com.dara.restweathersensorapp.api.SensorDataRetrieveApi;
import com.dara.restweathersensorapp.impl.SensorDataRetrieveApiImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {SensorDataRetrieveApi.class, SensorDataRetrieveApiImpl.class})
@WebMvcTest
public class SensorRegistrationApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("/addSensor")
    class RegistrationSensorEndpointTest {
        @Test
        public void GIVEN_requestWithValidData_WHEN_registrationAttempted_THEN_registrationSuccessfulAndSensorAddedToDatabase() throws Exception {

        }

        @Test
        public void GIVEN_requestWithDuplicatedData_WHEN_registrationAttempted_THEN_responseDetailsThatSensorAlreadyExists() throws Exception {

        }

        @Test
        public void GIVEN_requestWithInvalidData_WHEN_registrationAttempted_THEN_responseDetailsThatSensorCannotBeRegistered() throws Exception {

        }
    }

    @Nested
    @DisplayName("/replaceSensor/{oldSensorId}")
    class ReplacementSensorEndpointTest {
        @Test
        public void GIVEN_requestWithValidData_WHEN_replacementAttempted_THEN_responseDetailsThatSensorReplacementSuccessful() throws Exception {

        }

        @Test
        public void GIVEN_attemptToReplaceNonExistentSensor_WHEN_replacementAttempted_THEN_responseDetailsThatSensorReplacementFailed() throws Exception {

        }

        @Test
        public void GIVEN_requestWithInvalidData_WHEN_replacementAttempted_THEN_responseDetailsThatSensorCannotBeReplaced() throws Exception {

        }
    }

    @Nested
    @DisplayName("/deleteSensor/{sensorId}")
    class DeleteSensorEndpointTest {
        @Test
        public void GIVEN_requestWithValidData_WHEN_deletionAttempted_THEN_responseDetailsThatSensorDeletedSuccessful() throws Exception {

        }

        @Test
        public void GIVEN_attemptToDeleteNonExistentSensor_WHEN_deletionAttempted_THEN_responseDetailsThatSensorDeletionFailed() throws Exception {

        }
    }
}