package com.dara.restweathersensorapp.impl;

import com.dara.restweathersensorapp.SensorRepository;
import com.dara.restweathersensorapp.data.Sensor;
import com.dara.restweathersensorapp.exception.DuplicateSensorException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class SensorRegistrationApiImplTest {

    @Autowired
    SensorRepository sensorRepository;

    private final Sensor.SensorBuilder sb = new Sensor.SensorBuilder();

    private SensorRegistrationApiImpl sensorRegistrationApi;

    @BeforeEach
    void setup() {
        sensorRegistrationApi = new SensorRegistrationApiImpl(sensorRepository);
    }

    @Nested
    @DisplayName("addSensor")
    class AddSensorTest {
        @Test
        public void GIVEN_callWithValidData_WHEN_addSensorCalled_THEN_registrationSuccessful() {
            final Sensor sensor = sb.sensorId(1L).country("IE").city("Mayo").build();
            sensorRegistrationApi.addSensor(sensor);

            assertEquals(1, sensorRepository.findAll().size());
            assertEquals("IE", sensorRepository.findById(1L).get().getCountry());
            assertEquals("Mayo", sensorRepository.findById(1L).get().getCity());
        }

        @Test
        public void GIVEN_callWithDuplicatedData_WHEN_addSensorCalled_THEN_exceptionThrown() {
            final Sensor sensor = sb.sensorId(1L).country("IE").city("Mayo").build();
            sensorRegistrationApi.addSensor(sensor);

            final Sensor sensorWithDuplicateId = sb.sensorId(1L).country("IE").city("Dublin").build();
            final DuplicateSensorException duplicateSensorException = Assertions.assertThrows(DuplicateSensorException.class,
                    () -> sensorRegistrationApi.addSensor(sensorWithDuplicateId));
            assertEquals("Sensor with ID:1 already exists. To replace an already existing sensor please use \"replaceSensor\" method", duplicateSensorException.getMessage());
        }
    }

    @Nested
    @DisplayName("replaceSensor")
    class ReplacementSensorEndpointTest {
        @Test
        public void GIVEN_callWithValidData_WHEN_replaceSensorCalled_THEN_sensorReplacementSuccessful() {
            final Sensor sensor = sb.sensorId(1L).country("IE").city("Mayo").build();
            sensorRegistrationApi.addSensor(sensor);

            final Sensor replacementSensor = sb.sensorId(1L).country("IE").city("Dublin").build();
            sensorRegistrationApi.replaceSensor(1L, replacementSensor);

            assertEquals(1, sensorRepository.findAll().size());
            assertEquals("IE", sensorRepository.findById(1L).get().getCountry());
            assertEquals("Dublin", sensorRepository.findById(1L).get().getCity());
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
