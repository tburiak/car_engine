package com.java.course.spring.data.car_engine.service;

import com.java.course.spring.data.car_engine.model.CarInfo;
import com.java.course.spring.data.car_engine.persistence.entity.CarEntity;
import com.java.course.spring.data.car_engine.persistence.entity.EngineEntity;
import com.java.course.spring.data.car_engine.persistence.repository.CarRepository;
import com.java.course.spring.data.car_engine.persistence.repository.EngineRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class DefaultVehicleService implements VehicleService {

    private final CarRepository carRepository;
    private final EngineRepository engineRepository;

    public List<CarEntity> getCars() {
        return carRepository.findAll();
    }

    @Override
    public List<EngineEntity> getEngines() {
        return engineRepository.findAll();
    }

    @Override
    public List<CarInfo> getCarsInfo() {
        List<CarEntity> carEntities = carRepository.findAll();
        List<CarInfo> carsInfo = new ArrayList<>();
        carEntities.stream()
                .filter(car -> Objects.nonNull(car.getEngineEntity()))
                .forEach(car -> carsInfo.add(
                        CarInfo.builder()
                                .carEntity(car)
                                .engineTitle(engineRepository.getReferenceById(car.getEngineEntity().getId()).getTitle())
                                .build())
                );
        return carsInfo;
    }


}
