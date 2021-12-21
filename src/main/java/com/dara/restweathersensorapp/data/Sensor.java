package com.dara.restweathersensorapp.data;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
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

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Map<String, WeatherData> getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(Map<String, WeatherData> weatherData) {
        this.weatherData = weatherData;
    }
}
