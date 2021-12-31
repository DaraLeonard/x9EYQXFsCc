package com.dara.restweathersensorapp.data;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class WeatherData {

    private LocalDateTime weatherMetricTime;
    private String weatherMetricName;
    private Double weatherMetricValue;

    public WeatherData() {

    }

    public WeatherData(final LocalDateTime weatherMetricTime, final String weatherMetricName, final Double weatherMetricValue) {
        this.weatherMetricTime = weatherMetricTime;
        this.weatherMetricName = weatherMetricName;
        this.weatherMetricValue = weatherMetricValue;
    }

    public String getWeatherMetricName() {
        return weatherMetricName;
    }

    public Double getWeatherMetricValue() {
        return weatherMetricValue;
    }

    public LocalDateTime getWeatherMetricTime() {
        return weatherMetricTime;
    }

    @Override public String toString() {
        return "WeatherData{" +
                "weatherMetricTime=" + weatherMetricTime +
                ", weatherMetricName='" + weatherMetricName + '\'' +
                ", weatherMetricValue=" + weatherMetricValue +
                '}';
    }
}
