package com.dara.restweathersensorapp.impl;

import com.dara.restweathersensorapp.api.SensorDataAddApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SensorDataAddApiImpl implements SensorDataAddApi {

    public SensorDataAddApiImpl() {

    }

    @Override
    public ResponseEntity<?> addMetricValue(@PathVariable Long sensorId, @PathVariable String metricName, @PathVariable Double metricValue) {
        return null;
    }
}
