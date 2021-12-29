package com.dara.restweathersensorapp.impl;


import com.dara.restweathersensorapp.SensorRepository;
import com.dara.restweathersensorapp.api.SensorRegistrationApi;
import com.dara.restweathersensorapp.data.Sensor;
import com.dara.restweathersensorapp.exception.DuplicateSensorException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class SensorRegistrationApiImpl implements SensorRegistrationApi {

    private final SensorRepository sensorRepository;

    public SensorRegistrationApiImpl(final SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public ResponseEntity<?> addSensor(final Sensor sensor) {
        if(sensorRepository.findById(sensor.getSensorId()).isPresent()) {
            throw new DuplicateSensorException(sensor.getSensorId());
        }

        final EntityModel<Sensor> entityModel = EntityModel.of(sensorRepository.save(sensor),
                linkTo(methodOn(SensorDataRetrieveApiImpl.class).getSensorById(sensor.getSensorId())).withSelfRel(),
                linkTo(methodOn(SensorDataRetrieveApiImpl.class).getAllSensors()).withRel("sensors"));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
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
