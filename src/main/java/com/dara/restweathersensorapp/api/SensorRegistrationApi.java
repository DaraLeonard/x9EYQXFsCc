package com.dara.restweathersensorapp.api;

import com.dara.restweathersensorapp.data.Sensor;
import com.dara.restweathersensorapp.exception.SensorNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface SensorRegistrationApi {

    /**
     * Method to register a sensor
     *
     * @param sensor to register
     * @return a {@code ResponseEntity} containing information on the response
     */
    @PostMapping("/addSensor")
    ResponseEntity<?> addSensor(final Sensor sensor);

    /**
     * Method to replace an already existing sensor
     *
     * @param oldSensorId the ID of the {@link Sensor} to be replaced
     * @param newSensor   the replacement {@link Sensor}
     * @return a {@code ResponseEntity} containing information on the response
     * @throws SensorNotFoundException if a client attempts to replace a non-registered {@link Sensor}
     */
    @PutMapping("/replaceSensor/{oldSensorId}")
    ResponseEntity<?> replaceSensor(@PathVariable Long oldSensorId, @RequestBody Sensor newSensor) throws SensorNotFoundException;

    /**
     * Method to delete a registered {@link Sensor}
     *
     * @param sensorId the ID of the {@link Sensor} to be deleted
     * @return a {@code ResponseEntity} containing information on the response
     * @throws SensorNotFoundException if a client attempts to delete a non-registered {@link Sensor}
     */
    @DeleteMapping("/deleteSensor/{sensorId}")
    ResponseEntity<?> deleteSensor(@PathVariable Long sensorId) throws SensorNotFoundException;
}
