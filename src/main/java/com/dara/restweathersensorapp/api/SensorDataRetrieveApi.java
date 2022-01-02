package com.dara.restweathersensorapp.api;

import com.dara.restweathersensorapp.data.Sensor;
import com.dara.restweathersensorapp.exception.SensorNotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface SensorDataRetrieveApi {

    /**
     * Method returns a collection of all registered {@link Sensor} objects
     *
     * @return a collection of all registered {@link Sensor} objects
     */
    @GetMapping("/sensors")
    CollectionModel<EntityModel<Sensor>> getAllSensors();

    /**
     * Method returns an {@link EntityModel} object containing a {@link Sensor} object. If a client
     * attempts to query a sensor whose ID is not registered, a {@link SensorNotFoundException} will be thrown.
     *
     * @param sensorId the ID of the sensor to return
     * @return an {@link EntityModel} object containing a {@link Sensor} object
     * @throws SensorNotFoundException if the supplied {@link Sensor} ID is not registered
     */
    @GetMapping("/sensor/{sensorId}")
    EntityModel<Sensor> getSensorById(@PathVariable Long sensorId) throws SensorNotFoundException;

    /**
     * Method that returns data from the registered {@link Sensor} objects.
     * <p>
     * This method accepts queries for multiple sensors. To query multiple sensors the wildcard character can be used ({@literal *}), or
     * all sensor ID(s) can be supplied via a comma seperated {@code String}, i.e. "1,2,3".
     * <p>
     * This method returns the average weather data over a previous configurable amount of time. The dateRange argument controls the span
     * of time the average will be returned for, i.e. supplying "7" will return the average over the previous week. The query the latest
     * data this argument can be null, or use {@link SensorDataRetrieveApi#getSensorData(String, String)}.
     * <p>
     * This method accepts queries for multiple weather attributes. To query all attributes the wildcard character can be used
     * ({@literal *}), or multiple attributes can be supplied via a comma seperated {@code String}, i.e. "humidity, wind-speed".
     *
     * @param sensorIds        the {@link Sensor} ID(s) the data is requested from
     * @param dateRange        an {@link Integer} representing the span of days the average weather data should be returned for
     * @param weatherAttribute the list of weather attributes for which the client is interested
     * @return a {@link ResponseEntity} containing the requested data
     * @throws SensorNotFoundException if the supplied {@link Sensor} ID(s) is not registered
     */
    @GetMapping("/sensorData/{sensorIds}/{dateRange}/{weatherAttribute}")
    ResponseEntity<?> getSensorData(@PathVariable String sensorIds, @PathVariable Integer dateRange, @PathVariable String weatherAttribute)
            throws SensorNotFoundException;

    /**
     * Method that returns data from the registered {@link Sensor} objects. This method returns the latest weather data for a particular sensor
     * <p>
     * This method accepts queries for multiple sensors. To query multiple sensors the wildcard character can be used ({@literal *}), or
     * all sensor ID(s) can be supplied via a comma seperated {@code String}, i.e. "1,2,3".
     * <p>
     * This method accepts queries for multiple weather attributes. To query all attributes the wildcard character can be used
     * ({@literal *}), or multiple attributes can be supplied via a comma seperated {@code String}, i.e. "humidity, wind-speed".
     *
     * @param sensorIds        the {@link Sensor} ID(s) the data is requested from
     * @param weatherAttribute the list of weather attributes for which the client is interested
     * @return a {@link ResponseEntity} containing the requested data
     * @throws SensorNotFoundException if the supplied {@link Sensor} ID(s) is not registered
     */
    @GetMapping("/sensorData/{sensorIds}/{weatherAttribute}")
    ResponseEntity<?> getSensorData(@PathVariable String sensorIds, @PathVariable String weatherAttribute) throws SensorNotFoundException;
}
