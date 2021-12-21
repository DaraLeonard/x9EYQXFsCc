package com.dara.restweathersensorapp.api;

import com.dara.restweatherapp.Sensor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface SensorDataRetrieveApi {

    /**
     * return a list off all sensor ids + location data
     */
    @GetMapping("/sensors")
    CollectionModel<EntityModel<Sensor>> getAllSensors();

    /**
     * should return all data on specific sensor(s)
     */
    @GetMapping("/sensor/{sensorId}")
    EntityModel<Sensor> getSensorById(@PathVariable Long sensorId);

    /**
     * should take in 1 or more IDs
     * should take in date range between 1 and 30 days. If absent return latest value
     * weatherAttribute - if present return only that attribute, if missing return all
     */
    @GetMapping("/sensor/{sensorIds}/{dateRange}/{weatherAttribute}")
    EntityModel<Sensor> getSensorData(@PathVariable Long sensorIds, @RequestParam Optional<Integer> dateRange, @RequestParam Optional<String> weatherAttribute);
}
