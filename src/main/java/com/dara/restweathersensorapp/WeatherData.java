package com.dara.restweathersensorapp;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class WeatherData {

    private LocalDateTime timeOfReading;
    private Double weatherData;

    public LocalDateTime getTimeOfReading() {
        return timeOfReading;
    }

    public void setTimeOfReading(LocalDateTime timeOfReading) {
        this.timeOfReading = timeOfReading;
    }

    public Double getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(Double weatherData) {
        this.weatherData = weatherData;
    }

    public WeatherData() {

    }

}
