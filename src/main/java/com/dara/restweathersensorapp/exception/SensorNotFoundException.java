package com.dara.restweathersensorapp.exception;

public class SensorNotFoundException extends RuntimeException{

    public SensorNotFoundException(final Long sensorId) {
        super(String.format("Sensor with ID:%s could not be found", sensorId));
    }
}