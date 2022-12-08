package com.java.course.spring.data.car_engine.controller;

import com.java.course.spring.data.car_engine.model.CarInfo;
import com.java.course.spring.data.car_engine.persistence.entity.CarEntity;
import com.java.course.spring.data.car_engine.persistence.entity.EngineEntity;
import com.java.course.spring.data.car_engine.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping(value = "/cars")
    public List<CarEntity> getCars() {
        return vehicleService.getCars();
    }

    @GetMapping(value = "/cars/info")
    public List<CarInfo> getCarsInfo() {
        return vehicleService.getCarsInfo();
    }

    @GetMapping(value = "/engines")
    public List<EngineEntity> getEngines() {
        return vehicleService.getEngines();
    }




}
