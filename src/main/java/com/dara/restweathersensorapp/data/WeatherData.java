package com.dara.restweathersensorapp.data;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class WeatherData {

    private LocalDateTime timeOfReading;
    private Double weatherData;

    public WeatherData() {

    }

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

    @Override
    public String toString() {
        return "WeatherData{" +
                "timeOfReading=" + timeOfReading +
                ", weatherData=" + weatherData +
                '}';
    }
}
