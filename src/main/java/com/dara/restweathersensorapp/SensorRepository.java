package com.dara.restweathersensorapp;

import com.dara.restweathersensorapp.data.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, Long> {
}