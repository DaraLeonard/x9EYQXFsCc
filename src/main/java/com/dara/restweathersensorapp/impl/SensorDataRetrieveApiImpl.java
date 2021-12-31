package com.dara.restweathersensorapp.impl;


import com.dara.restweathersensorapp.SensorRepository;
import com.dara.restweathersensorapp.api.SensorDataRetrieveApi;
import com.dara.restweathersensorapp.data.Sensor;
import com.dara.restweathersensorapp.data.WeatherData;
import com.dara.restweathersensorapp.exception.SensorNotFoundException;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.json.JSONObject;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        final List<EntityModel<Sensor>> sensors = sensorRepository.findAll().stream()
                .map(sensor -> EntityModel.of(sensor,
                        linkTo(methodOn(SensorDataRetrieveApiImpl.class).getSensorById(sensor.getSensorId())).withSelfRel(),
                        linkTo(methodOn(SensorDataRetrieveApiImpl.class).getAllSensors()).withRel("sensors")))
                .collect(Collectors.toList());

        return CollectionModel.of(sensors, linkTo(methodOn(SensorDataRetrieveApiImpl.class).getAllSensors()).withSelfRel());
    }

    @Override
    public EntityModel<Sensor> getSensorById(final @PathVariable Long sensorId) {
        final Sensor sensor = sensorRepository.findById(sensorId).orElseThrow(() -> new SensorNotFoundException(sensorId));

        return EntityModel.of(sensor,
                linkTo(methodOn(SensorDataRetrieveApiImpl.class).getSensorById(sensorId)).withSelfRel(),
                linkTo(methodOn(SensorDataRetrieveApiImpl.class).getAllSensors()).withRel("sensors"));
    }

    @Override
    public JSONObject getSensorData(final @PathVariable String sensorIds, final @PathVariable Integer dateRange,
                                    final @PathVariable String weatherAttribute) {
        final Map<String, String> sensorDataMap = new HashMap<>();
        final Collection<EntityModel<Sensor>> sensorList = getSensorsBasedOnQuery(sensorIds);
        final List<String> weatherAttributes = Arrays.stream(weatherAttribute.split(",")).collect(Collectors.toList());
        final Collection<EntityModel<Sensor>> sensorListWithFilteredWeatherAttributes = getDataWithFilteredWeatherAttributes(sensorList,
                weatherAttributes);

        if (dateRange == null) {
            //TODO: implement functionality that will return the latest data ONLY if the date range is not supplied. Implementation would
            // be similar to that below. Take the weather attributes of interest and cycle through them to find that latest value of each
            // . This would then be added to the map and returned
        } else {
            sensorListWithFilteredWeatherAttributes.forEach(sensorEntityModel -> {
                final List<WeatherData> weatherData = sensorEntityModel.getContent().getWeatherData();
                final Stream<String> uniqueWeatherAttributes = weatherData.stream().map(WeatherData::getWeatherMetricName).distinct();

                uniqueWeatherAttributes.forEach(currentWeatherAttribute -> {
                    final DescriptiveStatistics stats = new DescriptiveStatistics();
                    stats.setWindowSize(100);
                    weatherData.forEach(weatherDataEntry -> {
                        if (weatherDataEntry.getWeatherMetricName().equals(currentWeatherAttribute) &&
                                isWeatherDataWithinRangeOfQuery(dateRange, weatherDataEntry)) {
                            stats.addValue(weatherDataEntry.getWeatherMetricValue());

                            sensorDataMap.put(sensorEntityModel.getContent().getSensorId() + "-" + currentWeatherAttribute,
                                    String.valueOf(stats.getMean()));
                        }
                    });

                });
            });
        }

        return new JSONObject(sensorDataMap);
    }

    @Override
    public JSONObject getSensorData(final @PathVariable String sensorIds, final @PathVariable String weatherAttribute) {
        return this.getSensorData(sensorIds, null, weatherAttribute);
    }

    private Collection<EntityModel<Sensor>> getSensorsBasedOnQuery(final String sensorIds) {
        if (sensorIds.equals("*")) {
            return this.getAllSensors().getContent();
        } else {
            final Collection<EntityModel<Sensor>> sensorList = new ArrayList<>();
            final String[] splitIds = sensorIds.split(",");
            final List<Long> sensorIdList = Arrays.stream(splitIds).map(Long::parseLong).collect(Collectors.toList());
            sensorIdList.forEach(sensorId -> sensorList.add(this.getSensorById(sensorId)));
            return sensorList;
        }
    }

    private Collection<EntityModel<Sensor>> getDataWithFilteredWeatherAttributes(final Collection<EntityModel<Sensor>> sensorList,
                                                                                 final List<String> weatherAttributes) {
        if (!weatherAttributes.get(0).equals("*")) {
            sensorList.forEach(sensorEntityModel -> {
                final Sensor content = sensorEntityModel.getContent();
                final List<WeatherData> collect = content.getWeatherData().stream()
                        .filter(weatherData -> weatherAttributes.contains(weatherData.getWeatherMetricName())).collect(
                                Collectors.toList());
                content.setWeatherData(collect);
            });

        }
        return sensorList;
    }

    private boolean isWeatherDataWithinRangeOfQuery(Integer dateRange, WeatherData weatherData1) {
        return weatherData1.getWeatherMetricTime().plusDays(dateRange).isAfter(
                LocalDateTime.now());
    }
}
