package com.dara.restweathersensorapp.impl;

import com.dara.restweatherapp.Sensor;
import com.dara.restweatherapp.SensorRepository;
import com.dara.restweatherapp.exceptionhandling.SensorNotFoundException;
import com.dara.restweathersensorapp.api.SensorDataRetrieveApi;
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
public class SensorDataRetrieverApiImpl implements SensorDataRetrieveApi {

    public SensorDataRetrieverApiImpl() {
    }

    @Override
    public CollectionModel<EntityModel<Sensor>> getAllSensors() {
        return null;
    }

    @Override
    public EntityModel<Sensor> getSensorById(@PathVariable Long sensorId) {
        return null;
    }

    @Override
    public EntityModel<Sensor> getSensorData(Long sensorIds, Optional<Integer> dateRange, Optional<String> weatherAttribute) {
        return null;
    }
}
