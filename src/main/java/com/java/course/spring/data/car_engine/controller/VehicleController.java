package com.java.course.spring.data.car_engine.controller;

import com.java.course.spring.data.car_engine.model.CarEngineRequest;
import com.java.course.spring.data.car_engine.model.CarRequest;
import com.java.course.spring.data.car_engine.model.EngineRequest;
import com.java.course.spring.data.car_engine.model.HumanRequest;
import com.java.course.spring.data.car_engine.persistence.entity.CarEntity;
import com.java.course.spring.data.car_engine.persistence.entity.EngineEntity;
import com.java.course.spring.data.car_engine.persistence.entity.HumanEntity;
import com.java.course.spring.data.car_engine.service.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createNoEngineCar(@RequestBody @Valid CarRequest carRequest) {
        vehicleService.createNoEngineCar(carRequest);
        return ResponseEntity.ok("The no engine car is created");
    }

    @PostMapping(value = "/car/engine")
    public ResponseEntity<String> createCarWithNewEngine(@RequestBody @Valid CarEngineRequest carEngineRequest) {
        vehicleService.createCarWithNewEngine(carEngineRequest);
        return ResponseEntity.ok("The car with new engine is created");
    }

    @PostMapping(value = "/car/engine/{engineId}")
    public ResponseEntity<String> createCarWithExistingEngine(@PathVariable int engineId, @RequestBody @Valid CarRequest carRequest) {
        vehicleService.createCarWithExistingEngine(engineId, carRequest);
        return ResponseEntity.ok("The car with existing engine is created");
    }

    @PostMapping(value = "/car/{carId}/human/{humanId}")
    public ResponseEntity<String> addExistingCarToExistingHuman(@PathVariable int carId, @PathVariable int humanId) {
        vehicleService.addExistingCarToExistingHuman(carId, humanId);
        return ResponseEntity.ok("The existing car is added to existing human");
    }

    @PostMapping(value = "/engine")
    public ResponseEntity<String> createEngine(@RequestBody @Valid EngineRequest engineRequest) {
        log.info("Creating engine : {}", engineRequest);
        vehicleService.createEngine(engineRequest);
        return ResponseEntity.ok("The engine is created");
    }

    @GetMapping(value = "/engines")
    public List<EngineEntity> getEngines() {
        return vehicleService.getEngines();
    }

    @DeleteMapping(value = "car/{carId}")
    public ResponseEntity<String> deleteCarById(@PathVariable int carId) {
        vehicleService.deleteCarById(carId);
        return ResponseEntity.ok("The car is deleted");
    }

    @DeleteMapping(value = "engine/{engineId}")
    public ResponseEntity<String> deleteEngineById(@PathVariable int engineId) {
        vehicleService.deleteEngineById(engineId);
        return ResponseEntity.ok("The engine is deleted");
    }

    @GetMapping(value = "cars/no-engine")
    public List<CarEntity> getNoEngineCars() {
        return vehicleService.getNoEngineCars();
    }

    @PutMapping(value = "car/{carId}/engine")
    public ResponseEntity<String> updateCarWithEngine(@PathVariable int carId, @RequestBody CarEngineRequest carEngineRequest) {
        vehicleService.updateCarWithEngine(carId, carEngineRequest);
        return ResponseEntity.ok("The car is updated");
    }

    @GetMapping(value = "humans")
    public List<HumanEntity> getHumans() {return vehicleService.getHumans();}

    @GetMapping(value = "humans/no-car")
    public List<HumanEntity> getNoCarHuman() {
        return vehicleService.getNoCarHumans();
    }

    @PostMapping(value = "human")
    public ResponseEntity<String> createHuman(@RequestBody HumanRequest humanRequest) {
        vehicleService.createHuman(humanRequest);
        return ResponseEntity.ok("The human is created");
    }

    @PutMapping(value = "human/{humanId}")
    public ResponseEntity<String> updateHuman(@PathVariable int humanId, @RequestBody HumanRequest humanRequest) {
        vehicleService.updateHumanById(humanId, humanRequest);
        return ResponseEntity.ok("The human is updated");
    }

    @DeleteMapping(value = "human/{humanId}")
    public ResponseEntity<String> deleteHumanById(@PathVariable int humanId) {
        vehicleService.deleteHumanById(humanId);
        return ResponseEntity.ok("The human is deleted");
    }
}
