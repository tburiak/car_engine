package com.java.course.spring.data.car_engine.service;

import com.java.course.spring.data.car_engine.model.CarEngineRequest;
import com.java.course.spring.data.car_engine.model.CarRequest;
import com.java.course.spring.data.car_engine.model.EngineRequest;
import com.java.course.spring.data.car_engine.persistence.entity.CarEntity;
import com.java.course.spring.data.car_engine.persistence.entity.EngineEntity;
import com.java.course.spring.data.car_engine.persistence.repository.CarRepository;
import com.java.course.spring.data.car_engine.persistence.repository.EngineRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
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
    public void createNoEngineCar(CarRequest carRequest) {
        CarEntity car = CarEntity.builder()
                .title(carRequest.getTitle())
                .build();
        log.info("The noEngine car is created:{}", carRepository.save(car));
    }

    @Override
    public void createCarWithNewEngine(CarEngineRequest carEngineRequest) {
        EngineEntity engine = EngineEntity.builder()
                .title(carEngineRequest.getEngine().getTitle())
                .build();
        CarEntity car = CarEntity.builder()
                .engineEntity(engine).title(carEngineRequest.getTitle())
                .build();
        log.info("The car with new engine is created:{}", carRepository.save(car));
    }

    @Override
    public void createCarWithExistingEngine(int engineId, CarRequest carRequest) {
        engineRepository.findById(engineId)
                .filter(this::isEngineAvailable)
                .ifPresentOrElse(engine -> {
                    CarEntity car = CarEntity.builder()
                            .engineEntity(engine).title(carRequest.getTitle())
                            .build();
                    log.info("The car with existing engine is created: {}", carRepository.save(car));
                }, () -> {
                    String errorMessage = String.format("No available engine found by id: %s", engineId);
                    log.error(errorMessage);
                    throw new IllegalArgumentException(errorMessage);
                });
    }

    @Override
    public void createEngine(EngineRequest engineRequest) {
        EngineEntity engine = EngineEntity.builder().title(engineRequest.getTitle()).build();
        log.info("The engine is created: {}", engineRepository.save(engine));
    }

    @Override
    public void deleteCarById(int carId) {
        carRepository.deleteById(carId);
        log.info("The car with id {} is deleted", carId);
    }

    @Override
    public void deleteEngineById(int engineId) {
        carRepository.findByEngineEntityId(engineId)
                .ifPresentOrElse(carEntity -> {
                    carEntity.setEngineEntity(null);
                    carRepository.save(carEntity);
                    engineRepository.deleteById(engineId);
                }, () -> engineRepository.deleteById(engineId));
        log.info("The engine with id {} is deleted", engineId);
    }

    @Override
    public List<CarEntity> getNoEngineCars() {
        return carRepository.findAll().stream()
                .filter(car -> Objects.isNull(car.getEngineEntity()))
                .collect(Collectors.toList());
    }

    private boolean isEngineAvailable(EngineEntity engine) {
        return carRepository.findAll().stream()
                .filter(carEntity -> !Objects.isNull(carEntity.getEngineEntity()))
                .noneMatch(carEntity -> carEntity.getEngineEntity().getId() == engine.getId());
    }

}
