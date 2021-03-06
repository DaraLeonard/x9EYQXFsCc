package com.dara.restweathersensorapp.data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Sensor(final SensorBuilder sensorBuilder) {
        this.sensorId = sensorBuilder.sensorId;
        this.city = sensorBuilder.city;
        this.country = sensorBuilder.country;
        this.weatherData = sensorBuilder.weatherData;
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

    public void setWeatherData(final List<WeatherData> weatherData) {
        this.weatherData = weatherData;
    }

    @Override public int hashCode() {
        return Objects.hash(sensorId, country, city, weatherData);
    }

    public static class SensorBuilder {

        private Long sensorId;
        private String city;
        private String country;
        private List<WeatherData> weatherData = new ArrayList<>();

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

        public SensorBuilder weatherData(final List<WeatherData> weatherData){
            this.weatherData = weatherData;
            return SensorBuilder.this;
        }

        public SensorBuilder addWeatherData(final WeatherData weatherData){
            this.weatherData.add(weatherData);
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
