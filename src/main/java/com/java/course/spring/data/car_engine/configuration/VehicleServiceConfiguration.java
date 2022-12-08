package com.java.course.spring.data.car_engine.configuration;

import com.java.course.spring.data.car_engine.persistence.repository.CarRepository;
import com.java.course.spring.data.car_engine.persistence.repository.EngineRepository;
import com.java.course.spring.data.car_engine.service.DefaultVehicleService;
import com.java.course.spring.data.car_engine.service.VehicleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VehicleServiceConfiguration {

    @Bean
    public VehicleService vehicleService(CarRepository carRepository, EngineRepository engineRepository) {
        return new DefaultVehicleService(carRepository, engineRepository);
    }
}
