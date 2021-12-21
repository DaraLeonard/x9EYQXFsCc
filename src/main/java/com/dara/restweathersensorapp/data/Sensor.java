package com.dara.restweathersensorapp.data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Sensor {

    private @Id
    Long sensorId;
    private String country;
    private String city;

    @ElementCollection
    private Map<String, WeatherData> weatherData;

    public Sensor() {

    }

    public Sensor(final Long sensorId, final String country, final String city) {
        this.sensorId = sensorId;
        this.country = country;
        this.city = city;

        weatherData = new HashMap<>();
    }

    public Sensor(final SensorBuilder sensorBuilder) {
        this.sensorId = sensorBuilder.sensorId;
        this.city = sensorBuilder.city;
        this.country = sensorBuilder.country;

        final WeatherData weatherData = new WeatherData();
        weatherData.setTimeOfReading(sensorBuilder.timeOfReading);
        weatherData.setWeatherData(sensorBuilder.valueOfReading);

        this.weatherData = Map.of(sensorBuilder.weatherMetricName, weatherData);
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "sensorId=" + sensorId +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", weatherData=" + weatherData +
                '}';
    }

    public static class SensorBuilder {

        private Long sensorId;
        private String city;
        private String country;
        private String weatherMetricName;
        private LocalDateTime timeOfReading;
        private Double valueOfReading;

        public SensorBuilder() {
        }

        public SensorBuilder sensorId(Long sensorId) {
            this.sensorId = sensorId;
            return SensorBuilder.this;
        }

        public SensorBuilder city(String city) {
            this.city = city;
            return SensorBuilder.this;
        }

        public SensorBuilder country(String country) {
            this.country = country;
            return SensorBuilder.this;
        }

        public SensorBuilder weatherMetricName(String weatherMetricName) {
            this.weatherMetricName = weatherMetricName;
            return SensorBuilder.this;
        }

        public SensorBuilder timeOfReading(LocalDateTime timeOfReading) {
            this.timeOfReading = timeOfReading;
            return SensorBuilder.this;
        }

        public SensorBuilder valueOfReading(Double valueOfReading) {
            this.valueOfReading = valueOfReading;
            return SensorBuilder.this;
        }

        public Sensor build() {
            if (this.sensorId == null) {
                throw new NullPointerException("The property \"sensorId\" is null. The properties \"sensorId\", \"city\" and \"country\" are required.");
            }
            if (this.city == null) {
                throw new NullPointerException("The property \"city\" is null. The properties \"sensorId\", \"city\" and \"country\" are required.");
            }
            if (this.country == null) {
                throw new NullPointerException("The property \"country\" is null. The properties \"sensorId\", \"city\" and \"country\" are required.");
            }

            return new Sensor(this);
        }
    }
}
