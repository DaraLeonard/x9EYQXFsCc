package com.dara.restweathersensorapp.impl;


import com.dara.restweathersensorapp.data.Sensor;
import com.dara.restweathersensorapp.api.SensorDataRetrieveApi;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class SensorDataRetrieveApiImpl implements SensorDataRetrieveApi {

    public SensorDataRetrieveApiImpl() {
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
