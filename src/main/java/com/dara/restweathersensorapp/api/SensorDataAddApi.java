package com.dara.restweathersensorapp.api;

import com.dara.restweathersensorapp.exception.SensorNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface SensorDataAddApi {

    /**
     * Method that can be used to update a weather metric on a registered sensor. New metrics can be added via this method. If a client
     * attempts to update a sensor whose ID is not registered, a {@link SensorNotFoundException} will be thrown.
     *
     * @param sensorId    the ID of the sensor on which the data is to be updated
     * @param metricName  the name of the metric that is to be updated
     * @param metricValue the value if the metric that is to be updated
     * @return a {@code ResponseEntity} containing information on the response
     * @throws SensorNotFoundException if data is added to an unregistered sensor
     */
    @PostMapping("/addMetric/{sensorId}/{metricName}/{metricValue}")
    ResponseEntity<?> addMetricValue(@PathVariable Long sensorId, @PathVariable String metricName, @PathVariable Double metricValue) throws
            SensorNotFoundException;
}
