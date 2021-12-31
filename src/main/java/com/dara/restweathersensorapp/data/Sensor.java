package com.dara.restweathersensorapp.data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sensor {

    private @Id
    Long sensorId;
    private String country;
    private String city;

    @ElementCollection
    private List<WeatherData> weatherData;

    public Sensor() {

    }

    public Sensor(final Long sensorId, final String country, final String city) {
        this.sensorId = sensorId;
        this.country = country;
        this.city = city;
        weatherData = new ArrayList<>();
    }

    public Sensor(final SensorBuilder sensorBuilder) {
        this.sensorId = sensorBuilder.sensorId;
        this.city = sensorBuilder.city;
        this.country = sensorBuilder.country;

        if (weatherData == null) {
            this.weatherData = new ArrayList<>();
        } else {
            this.weatherData = sensorBuilder.weatherData;
        }
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public List<WeatherData> getWeatherData() {
        return weatherData;
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
        private List<WeatherData> weatherData;

        public SensorBuilder() {
        }

        public SensorBuilder sensorId(final Long sensorId) {
            this.sensorId = sensorId;
            return SensorBuilder.this;
        }

        public SensorBuilder city(final String city) {
            this.city = city;
            return SensorBuilder.this;
        }

        public SensorBuilder country(final String country) {
            this.country = country;
            return SensorBuilder.this;
        }

        public SensorBuilder weatherData(final WeatherData... weatherData) {
            this.weatherData = List.of(weatherData);
            return SensorBuilder.this;
        }

        public Sensor build() {
            if (this.sensorId == null) {
                throw new NullPointerException(
                        "The property \"sensorId\" is null. The properties \"sensorId\", \"city\" and \"country\" are required.");
            }
            if (this.city == null) {
                throw new NullPointerException(
                        "The property \"city\" is null. The properties \"sensorId\", \"city\" and \"country\" are required.");
            }
            if (this.country == null) {
                throw new NullPointerException(
                        "The property \"country\" is null. The properties \"sensorId\", \"city\" and \"country\" are required.");
            }

            return new Sensor(this);
        }
    }
}
