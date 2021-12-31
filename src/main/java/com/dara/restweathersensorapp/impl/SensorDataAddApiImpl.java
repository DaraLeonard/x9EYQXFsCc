package com.dara.restweathersensorapp.impl;

import com.dara.restweathersensorapp.SensorRepository;
import com.dara.restweathersensorapp.api.SensorDataAddApi;
import com.dara.restweathersensorapp.data.Sensor;
import com.dara.restweathersensorapp.data.WeatherData;
import com.dara.restweathersensorapp.exception.SensorNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class SensorDataAddApiImpl implements SensorDataAddApi {

    private final SensorRepository sensorRepository;

    public SensorDataAddApiImpl(final SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    public ResponseEntity<?> addMetricValue(@PathVariable Long sensorId, @PathVariable String metricName, @PathVariable Double metricValue) {
        final Sensor sensor = sensorRepository.findById(sensorId).orElseThrow(() -> new SensorNotFoundException(sensorId));

        sensor.getWeatherData().add(new WeatherData(LocalDateTime.now(), metricName, metricValue));

        final EntityModel<Sensor> entityModel = EntityModel.of(sensorRepository.save(sensor),
                linkTo(methodOn(SensorDataRetrieveApiImpl.class).getSensorById(sensor.getSensorId())).withSelfRel(),
                linkTo(methodOn(SensorDataRetrieveApiImpl.class).getAllSensors()).withRel("sensors"));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}
