package com.dara.restweathersensorapp.api;

import com.dara.restweathersensorapp.data.Sensor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface SensorRegistrationApi {

    @PostMapping("/addSensor")
    ResponseEntity<?> addSensor(final Sensor sensor);

    @PutMapping("/replaceSensor/{oldSensorId}")
    ResponseEntity<?> replaceSensor(@PathVariable Long oldSensorId, @RequestBody Sensor newSensor);

    @DeleteMapping("/deleteSensor/{sensorId}")
    ResponseEntity<?> deleteSensor(@PathVariable Long sensorId);
}
