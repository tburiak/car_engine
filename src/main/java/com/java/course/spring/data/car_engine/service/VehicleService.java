package com.java.course.spring.data.car_engine.service;

import com.java.course.spring.data.car_engine.model.CarInfo;
import com.java.course.spring.data.car_engine.persistence.entity.CarEntity;
import com.java.course.spring.data.car_engine.persistence.entity.EngineEntity;

import java.util.List;

public interface VehicleService {
    List<CarEntity> getCars();
    List<EngineEntity> getEngines();
    List<CarInfo> getCarsInfo();

}
