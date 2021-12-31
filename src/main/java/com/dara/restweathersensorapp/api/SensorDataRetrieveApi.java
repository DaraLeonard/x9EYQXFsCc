package com.dara.restweathersensorapp.api;

import com.dara.restweathersensorapp.data.Sensor;
import org.json.JSONObject;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
     * weatherAttribute(s) - if present return only that attribute(s), can specify all (or *)
     * @return
     */
    @GetMapping("/sensorData/{sensorIds}/{dateRange}/{weatherAttribute}")
    JSONObject getSensorData(@PathVariable String sensorIds, @PathVariable Integer dateRange, @PathVariable String weatherAttribute);

    @GetMapping("/sensorData/{sensorIds}/{weatherAttribute}")
    JSONObject getSensorData(@PathVariable String sensorIds, @PathVariable String weatherAttribute);
}
