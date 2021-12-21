package com.dara.restweathersensorapp.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface SensorDataAddApi {

    /**
     * method to add weather data from a sensor.
     *
     * name of the metric (humidity, temp etc.) to be passed in as well as value.
     */
    @PostMapping("/addMetric/{sensorId}/{metricName}/{metricValue}")
    ResponseEntity<?> addMetricValue(@PathVariable Long sensorId, @PathVariable String metricName, @PathVariable Double metricValue);
}
