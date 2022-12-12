package com.java.course.spring.data.car_engine.service;

import com.java.course.spring.data.car_engine.model.CarEngineRequest;
import com.java.course.spring.data.car_engine.model.CarRequest;
import com.java.course.spring.data.car_engine.model.EngineRequest;
import com.java.course.spring.data.car_engine.persistence.entity.CarEntity;
import com.java.course.spring.data.car_engine.persistence.entity.EngineEntity;

import java.util.List;

public interface VehicleService {
    List<CarEntity> getCars();
    List<EngineEntity> getEngines();
    void createNoEngineCar(CarRequest carRequest);
    void createCarWithNewEngine(CarEngineRequest carEngineRequest);
    void createCarWithExistingEngine(int engineId, CarRequest carRequest);
    void createEngine(EngineRequest engineRequest);

    void deleteCarById(int carId);

    void deleteEngineById(int engineId);

    List<CarEntity> getNoEngineCars();
}
