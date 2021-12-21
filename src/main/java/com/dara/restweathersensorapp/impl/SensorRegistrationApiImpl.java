package com.dara.restweathersensorapp.impl;


import com.dara.restweathersensorapp.api.SensorRegistrationApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SensorRegistrationApiImpl implements SensorRegistrationApi {

    public SensorRegistrationApiImpl() {

    }

    @Override
    public ResponseEntity<?> addSensor(final Sensor sensor) {
        return null;
    }

    @Override
    public ResponseEntity<?> replaceSensor(@PathVariable Long oldSensorId, @RequestBody Sensor newSensor) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteSensor(@PathVariable Long sensorId) {
        return null;
    }
}
