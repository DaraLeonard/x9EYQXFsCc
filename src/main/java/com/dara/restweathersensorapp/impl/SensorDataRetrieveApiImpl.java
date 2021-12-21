package com.dara.restweathersensorapp.impl;


import com.dara.restweathersensorapp.SensorRepository;
import com.dara.restweathersensorapp.data.Sensor;
import com.dara.restweathersensorapp.api.SensorDataRetrieveApi;
import com.dara.restweathersensorapp.exception.SensorNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SensorDataRetrieveApiImpl implements SensorDataRetrieveApi {

    private final SensorRepository sensorRepository;

    public SensorDataRetrieveApiImpl(final SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public CollectionModel<EntityModel<Sensor>> getAllSensors() {
        List<EntityModel<Sensor>> sensors = sensorRepository.findAll().stream()
                .map(sensor -> EntityModel.of(sensor,
                        linkTo(methodOn(SensorDataRetrieveApiImpl.class).getSensorById(sensor.getSensorId())).withSelfRel(),
                        linkTo(methodOn(SensorDataRetrieveApiImpl.class).getAllSensors()).withRel("sensors")))
                .collect(Collectors.toList());

        return CollectionModel.of(sensors, linkTo(methodOn(SensorDataRetrieveApiImpl.class).getAllSensors()).withSelfRel());
    }

    @Override
    public EntityModel<Sensor> getSensorById(@PathVariable Long sensorId) {
        final Sensor sensor = sensorRepository.findById(sensorId).orElseThrow(() -> new SensorNotFoundException(sensorId));

        return EntityModel.of(sensor,
                linkTo(methodOn(SensorDataRetrieveApiImpl.class).getSensorById(sensorId)).withSelfRel(),
                linkTo(methodOn(SensorDataRetrieveApiImpl.class).getAllSensors()).withRel("sensors"));
    }

    @Override
    public EntityModel<Sensor> getSensorData(Long sensorIds, Optional<Integer> dateRange, Optional<String> weatherAttribute) {
        return null;
    }
}
