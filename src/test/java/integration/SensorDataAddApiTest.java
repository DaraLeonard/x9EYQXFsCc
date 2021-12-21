package integration;

import com.dara.restweathersensorapp.api.SensorDataAddApi;
import com.dara.restweathersensorapp.api.SensorDataRetrieveApi;
import com.dara.restweathersensorapp.impl.SensorDataAddApiImpl;
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
@ContextConfiguration(classes = {SensorDataAddApi.class, SensorDataAddApiImpl.class})
@WebMvcTest
public class SensorDataAddApiTest {

    @Autowired
    private MockMvc mockMvc;

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
