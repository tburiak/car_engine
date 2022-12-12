package com.java.course.spring.data.car_engine.controller;

import com.java.course.spring.data.car_engine.model.CarEngineRequest;
import com.java.course.spring.data.car_engine.model.CarRequest;
import com.java.course.spring.data.car_engine.model.EngineRequest;
import com.java.course.spring.data.car_engine.persistence.entity.CarEntity;
import com.java.course.spring.data.car_engine.persistence.entity.EngineEntity;
import com.java.course.spring.data.car_engine.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Validated
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping(value = "/cars")
    public List<CarEntity> getCars() {
        return vehicleService.getCars();
    }

    @PostMapping(value = "/car")
    public void createNoEngineCar(@RequestBody @Valid CarRequest carRequest) {
        log.info("Creating no engine car : {}", carRequest);
        vehicleService.createNoEngineCar(carRequest);
    }

    @PostMapping(value = "/car/engine")
    public void createCarWithNewEngine(@RequestBody @Valid CarEngineRequest carEngineRequest) {
        log.info("Creating car with new engine : {}", carEngineRequest);
        vehicleService.createCarWithNewEngine(carEngineRequest);
    }

    @PostMapping(value = "/car/engine/{engineId}")
    public void createCarWithExistingEngine(@PathVariable int engineId, @RequestBody @Valid CarRequest carRequest) {
        log.info("Creating car:{} with existing engine:{} ", carRequest, engineId);
        vehicleService.createCarWithExistingEngine(engineId, carRequest);
    }

    @PostMapping(value = "/engine")
    public void createEngine(@RequestBody @Valid EngineRequest engineRequest) {
        log.info("Creating engine : {}", engineRequest);
        vehicleService.createEngine(engineRequest);
    }

    @GetMapping(value = "/engines")
    public List<EngineEntity> getEngines() {
        return vehicleService.getEngines();
    }

    @DeleteMapping(value = "car/{carId}")
    public void deleteCarById(@PathVariable int carId) {
        vehicleService.deleteCarById(carId);
    }

    @DeleteMapping(value = "engine/{engineId}")
    public void deleteEngineById(@PathVariable int engineId) {
        vehicleService.deleteEngineById(engineId);
    }

    @GetMapping(value = "cars/no-engine")
    public List<CarEntity> getNoEngineCars() {
        return vehicleService.getNoEngineCars();
    }


}
