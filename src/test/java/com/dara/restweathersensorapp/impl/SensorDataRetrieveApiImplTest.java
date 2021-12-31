package com.dara.restweathersensorapp.impl;

import com.dara.restweathersensorapp.SensorRepository;
import com.dara.restweathersensorapp.api.SensorDataRetrieveApi;
import com.dara.restweathersensorapp.data.Sensor;
import com.dara.restweathersensorapp.data.WeatherData;
import com.dara.restweathersensorapp.exception.SensorNotFoundException;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class SensorDataRetrieveApiImplTest {

    @Autowired
    SensorRepository sensorRepository;

    private SensorDataRetrieveApi sensorDataRetrieveApi;
    private Sensor.SensorBuilder sb;

    @BeforeEach
    void setup() {
        sb = new Sensor.SensorBuilder();

        sensorDataRetrieveApi = new SensorDataRetrieveApiImpl(sensorRepository);

        final Sensor sensorA =
                sb.sensorId(1L).country("IE").city("Mayo").weatherData(List.of(new WeatherData(LocalDateTime.MIN, "humidity", 90D))).build();
        final Sensor sensorB =
                sb.sensorId(2L).country("IE").city("Galway").weatherData(List.of(new WeatherData(LocalDateTime.MIN, "temperature", 33.1))).build();
        final Sensor sensorC =
                sb.sensorId(3L).country("IE").city("Cork").weatherData(List.of(new WeatherData(LocalDateTime.MIN, "humidity", 60D))).build();
        final Sensor sensorD =
                sb.sensorId(4L).country("IE").city("Dublin").weatherData(List.of(new WeatherData(LocalDateTime.MIN, "wind-speed", 33.1))).build();

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
            assertNotNull(sensor);
            assertEquals(1L, sensor.getSensorId());
            assertEquals("IE", sensor.getCountry());
            assertEquals("Mayo", sensor.getCity());
            assertEquals(1, sensor.getWeatherData().size());
            assertEquals("humidity", sensor.getWeatherData().get(0).getWeatherMetricName());
            assertEquals(90D, sensor.getWeatherData().get(0).getWeatherMetricValue());
        }

        @Test
        public void GIVEN_callForNonExistentSensor_WHEN_callExecuted_THEN_exceptionThrown() {
            final SensorNotFoundException sensorNotFoundException = Assertions.assertThrows(SensorNotFoundException.class,
                    () -> sensorDataRetrieveApi.getSensorById(100L));

            assertEquals("Sensor with ID:100 could not be found", sensorNotFoundException.getMessage());
        }
    }

    @Nested
    @DisplayName("getSensorData")
    class GetSensorDataTest {

        @Test
        public void GIVEN_callForAllSensors_WHEN_callExecuted_THEN_allSensorsReturned() {
            final Sensor sensorNY =
                    sb.sensorId(12L).country("US").city("NY").weatherData(List.of(new WeatherData(LocalDateTime.now(), "wind-speed", 23.1)))
                            .build();
            final Sensor sensorNJ =
                    sb.sensorId(13L).country("US").city("NJ").weatherData(List.of(new WeatherData(LocalDateTime.now(), "humidity", 60D),
                            new WeatherData(LocalDateTime.now(), "humidity", 61D),
                            new WeatherData(LocalDateTime.now(), "humidity", 62D),
                            new WeatherData(LocalDateTime.now(), "humidity", 63D),
                            new WeatherData(LocalDateTime.now(), "wind-speed", 25.1)
                    )).build();

            sensorRepository.save(sensorNY);
            sensorRepository.save(sensorNJ);


            final JSONObject sensorData = sensorDataRetrieveApi.getSensorData("*", 5, "*");

            assertEquals(3, sensorData.length());

            assertEquals("23.1", sensorData.get("12-wind-speed"));
            assertEquals("25.1", sensorData.get("13-wind-speed"));
            assertEquals("61.5", sensorData.get("13-humidity"));
        }

        @Test
        public void GIVEN_callForMultipleSensors_WHEN_callExecuted_THEN_specifiedSensorsReturned() {
            final Sensor sensorNY =
                    sb.sensorId(12L).country("US").city("NY").weatherData(List.of(new WeatherData(LocalDateTime.now(), "wind-speed", 23.1)))
                            .build();
            final Sensor sensorNJ =
                    sb.sensorId(13L).country("US").city("NJ").weatherData(List.of(new WeatherData(LocalDateTime.now(), "humidity", 60D),
                            new WeatherData(LocalDateTime.now(), "humidity", 61D),
                            new WeatherData(LocalDateTime.now(), "humidity", 62D),
                            new WeatherData(LocalDateTime.now(), "humidity", 63D),
                            new WeatherData(LocalDateTime.now(), "wind-speed", 25.1)
                    )).build();

            sensorRepository.save(sensorNY);
            sensorRepository.save(sensorNJ);


            final JSONObject sensorData = sensorDataRetrieveApi.getSensorData("12,13", 5, "*");

            assertEquals(3, sensorData.length());

            assertEquals("23.1", sensorData.get("12-wind-speed"));
            assertEquals("25.1", sensorData.get("13-wind-speed"));
            assertEquals("61.5", sensorData.get("13-humidity"));
        }

        @Test
        public void GIVEN_callForSingleSensor_WHEN_callExecuted_THEN_correctSensorReturned() {
            final Sensor sensorNY =
                    sb.sensorId(12L).country("US").city("NY").weatherData(List.of(new WeatherData(LocalDateTime.now(), "wind-speed", 23.1)))
                            .build();
            final Sensor sensorNJ =
                    sb.sensorId(13L).country("US").city("NJ").weatherData(List.of(new WeatherData(LocalDateTime.now(), "humidity", 60D),
                            new WeatherData(LocalDateTime.now(), "humidity", 61D),
                            new WeatherData(LocalDateTime.now(), "humidity", 62D),
                            new WeatherData(LocalDateTime.now(), "humidity", 63D),
                            new WeatherData(LocalDateTime.now(), "wind-speed", 25.1)
                    )).build();

            sensorRepository.save(sensorNY);
            sensorRepository.save(sensorNJ);


            final JSONObject sensorData = sensorDataRetrieveApi.getSensorData("13", 5, "*");

            assertEquals(2, sensorData.length());

            assertEquals("25.1", sensorData.get("13-wind-speed"));
            assertEquals("61.5", sensorData.get("13-humidity"));
        }

        @Test
        public void GIVEN_callForNonExistentSensor_WHEN_callExecuted_THEN_exceptionThrown() {
            final SensorNotFoundException sensorNotFoundException = Assertions.assertThrows(SensorNotFoundException.class,
                    () -> sensorDataRetrieveApi.getSensorData("100", 5, "*"));

            assertEquals("Sensor with ID:100 could not be found", sensorNotFoundException.getMessage());
        }

        @Test
        public void GIVEN_callWithMissingDateRange_WHEN_callExecuted_THEN_lastDataReturned() {

        }

        @Test
        public void GIVEN_callWithDateRange_WHEN_callExecuted_THEN_averageDataForRangeReturned() {

        }

        @Test
        public void GIVEN_callWithSpecificWeatherAttribute_WHEN_callExecuted_THEN_requestedWeatherMetricsReturned() {

        }

        @Test
        public void GIVEN_callWithNoWeatherAttribute_WHEN_callExecuted_THEN_allWeatherMetricsReturned() {
//            Sensor sensorNY = sb.sensorId(12L).country("US").city("NY").weatherMetricName("wind-speed").timeOfReading(LocalDateTime.now()).weatherMetricValue(23.1).build();
//            Sensor sensorNJ = sb.sensorId(13L).country("US").city("NJ").weatherMetricName("humidity").timeOfReading(LocalDateTime.now()).weatherMetricValue(60D).build();
//
//            //final WeatherData oldHumidity = new WeatherData("humidity", 44.4);
//            final Map<LocalDateTime, WeatherData> weatherData = sensorNJ.getWeatherData();
//            weatherData.put(LocalDateTime.MIN, new WeatherData("humidity", 44.4));
//            sensorNJ.setWeatherData(weatherData);
//            sensorRepository.save(sensorNY);
//            sensorRepository.save(sensorNJ);
//
//
//            final EntityModel<JSONObject> sensorData = sensorDataRetrieveApi.getSensorData("*","*");
        }
    }
}
