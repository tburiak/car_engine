package com.java.course.spring.data.car_engine.service;

import com.java.course.spring.data.car_engine.model.CarEngineRequest;
import com.java.course.spring.data.car_engine.model.CarRequest;
import com.java.course.spring.data.car_engine.model.EngineRequest;
import com.java.course.spring.data.car_engine.model.HumanRequest;
import com.java.course.spring.data.car_engine.persistence.entity.CarEntity;
import com.java.course.spring.data.car_engine.persistence.entity.EngineEntity;
import com.java.course.spring.data.car_engine.persistence.entity.HumanEntity;
import com.java.course.spring.data.car_engine.persistence.repository.CarRepository;
import com.java.course.spring.data.car_engine.persistence.repository.EngineRepository;
import com.java.course.spring.data.car_engine.persistence.repository.HumanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class DefaultVehicleService implements VehicleService {
    private final HumanRepository humanRepository;

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
        carRepository.save(car);
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
    @Transactional
    public void deleteEngineById(int engineId) {
        carRepository.findByEngineEntityId(engineId)
                .ifPresentOrElse(carEntity -> carEntity.setEngineEntity(null),
                        () -> engineRepository.deleteById(engineId)
                );
        log.info("The engine with id {} is deleted", engineId);
    }

    @Override
    public List<CarEntity> getNoEngineCars() {
        return carRepository.findByEngineEntityIsNull();
    }

    @Override
    @Transactional
    public void updateCarWithEngine(int carId, CarEngineRequest carEngineRequest) {
        CarEntity carToUpdate = carRepository.getReferenceById(carId);
        EngineEntity engineToUpdate = engineRepository.getReferenceById(carToUpdate.getEngineEntity().getId());
        engineToUpdate.setTitle(carEngineRequest.getEngine().getTitle());
        log.info("The engine with id {} is updated", engineToUpdate.getId());

        carToUpdate.setEngineEntity(engineToUpdate);
        carToUpdate.setTitle(carEngineRequest.getTitle());
        carRepository.save(carToUpdate);
        log.info("The car with id {} is updated", carToUpdate.getId());
    }

    @Override
    @Transactional
    public void addExistingCarToExistingHuman(int carId, int humanId) {
        CarEntity car = carRepository.getReferenceById(carId);
        HumanEntity human = humanRepository.getReferenceById(humanId);
        car.setHuman(human);
        carRepository.save(car);
    }

    @Override
    public List<HumanEntity> getHumans() {
        return humanRepository.findAll();
    }

    @Override
    public void deleteHumanById(int humanId) {
        humanRepository.deleteById(humanId);
    }

    @Override
    public List<HumanEntity> getNoCarHumans() {
        return humanRepository.findAllHumanWithoutCar();
    }

    @Override
    @Transactional
    public void updateHumanById(int humanId, HumanRequest humanRequest) {
        HumanEntity humanToUpdate = humanRepository.getReferenceById(humanId);
        humanToUpdate.setFirstName(humanRequest.getFirstName());
        humanRepository.save(humanToUpdate);
    }

    @Override
    public void createHuman(HumanRequest humanRequest) {
        HumanEntity humanToCreate = HumanEntity.builder().firstName(humanRequest.getFirstName()).build();
        humanRepository.save(humanToCreate);
    }

    private boolean isEngineAvailable(EngineEntity engine) {
        return carRepository.findByEngineEntityId(engine.getId()).isEmpty();
    }

}
