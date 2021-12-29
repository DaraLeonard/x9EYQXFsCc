package com.dara.restweathersensorapp.exception;

public class DuplicateSensorException extends RuntimeException {

    public DuplicateSensorException(final Long sensorId) {
        super(String.format("Sensor with ID:%s already exists. To replace an already existing sensor please use \"replaceSensor\" method", sensorId));
    }
}
