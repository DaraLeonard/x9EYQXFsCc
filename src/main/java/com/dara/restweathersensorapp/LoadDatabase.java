package com.dara.restweathersensorapp;

import com.dara.restweathersensorapp.api.SensorRegistrationApi;
import com.dara.restweathersensorapp.data.Sensor;
import com.dara.restweathersensorapp.data.WeatherData;
import com.dara.restweathersensorapp.impl.SensorRegistrationApiImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initDb(final SensorRepository sensorRepository) {
        final Sensor.SensorBuilder sensorBuilder = new Sensor.SensorBuilder();
        final SensorRegistrationApi sensorRegistrationApiImpl = new SensorRegistrationApiImpl(sensorRepository);

        sensorRegistrationApiImpl.addSensor(sensorBuilder.sensorId(1L).country("IE").city("Mayo").weatherData(
                List.of(new WeatherData(LocalDateTime.MIN, "humidity", 90D))).build());
        sensorRegistrationApiImpl.addSensor(sensorBuilder.sensorId(2L).country("IE").city("Galway")
                .weatherData(List.of(new WeatherData(LocalDateTime.MIN, "temperature", 33.1))).build());
        sensorRegistrationApiImpl.addSensor(sensorBuilder.sensorId(3L).country("IE").city("Cork")
                .weatherData(List.of(new WeatherData(LocalDateTime.MIN, "humidity", 60D))).build());
        sensorRegistrationApiImpl.addSensor(sensorBuilder.sensorId(4L).country("IE").city("Dublin")
                .weatherData(List.of(new WeatherData(LocalDateTime.MIN, "wind-speed", 33.1))).build());
        sensorRegistrationApiImpl.addSensor(sensorBuilder.sensorId(12L).country("US").city("NY")
                .weatherData(List.of(new WeatherData(LocalDateTime.now(), "wind-speed", 23.1)))
                .build());
        sensorRegistrationApiImpl.addSensor(sensorBuilder.sensorId(13L).country("US").city("NJ")
                .weatherData(List.of(new WeatherData(LocalDateTime.now(), "humidity", 60D),
                        new WeatherData(LocalDateTime.now(), "humidity", 61D),
                        new WeatherData(LocalDateTime.now(), "humidity", 62D),
                        new WeatherData(LocalDateTime.now(), "humidity", 63D),
                        new WeatherData(LocalDateTime.now(), "wind-speed", 25.1)
                )).build());


        return args -> System.out.println("Database preloaded with sensor data...");
    }

}
