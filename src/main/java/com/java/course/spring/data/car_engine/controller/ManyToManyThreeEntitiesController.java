package com.java.course.spring.data.car_engine.controller;

import com.java.course.spring.data.car_engine.model.BuildingRequest;
import com.java.course.spring.data.car_engine.service.ManyToManyThreeEntitiesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/three/tables", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Validated
public class ManyToManyThreeEntitiesController {

    private final ManyToManyThreeEntitiesService service;

    @PostMapping(value = "/user/building")
    public ResponseEntity<String> assignNewUserAndNewBuilding(@RequestBody BuildingRequest buildingRequest) {
        service.assignNewUserAndNewBuilding(buildingRequest);
        return ResponseEntity.ok("The new user and building are assigned");
    }

}
