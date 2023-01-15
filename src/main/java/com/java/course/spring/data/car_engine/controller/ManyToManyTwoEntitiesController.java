package com.java.course.spring.data.car_engine.controller;

import com.java.course.spring.data.car_engine.model.BuildingRequest;
import com.java.course.spring.data.car_engine.persistence.entity.Building1Entity;
import com.java.course.spring.data.car_engine.persistence.entity.User1Entity;
import com.java.course.spring.data.car_engine.service.ManyToManyTwoEntitiesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/two/tables", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
@Validated
public class ManyToManyTwoEntitiesController {

    private final ManyToManyTwoEntitiesService service;

    @GetMapping(value = "/buildings")
    public List<Building1Entity> getBuildings() {
        return service.getBuildings();
    }

    @PostMapping(value = "/building")
    public ResponseEntity<String> createBuilding(@RequestBody @Valid BuildingRequest buildingRequest) {
        service.createBuilding(buildingRequest);
        return ResponseEntity.ok("The building is created");
    }

    @PutMapping(value = "building/{id}")
    public ResponseEntity<String> updateBuildingById(@PathVariable int id, @RequestBody BuildingRequest buildingRequest) {
        service.updateBuildingById(id, buildingRequest);
        return ResponseEntity.ok("The building is updated by id: " + id);
    }

    @GetMapping(value = "/users")
    public List<User1Entity> getUsers() {
        return service.getUsers();
    }

    @PostMapping(value = "/user")
    public ResponseEntity<String> createUser() {
        service.createUser();
        return ResponseEntity.ok("The user is created");
    }

    @PutMapping(value = "/user/{userId}/building/{buildingId}")
    public ResponseEntity<String> addExistingBuildingToExistingUser(@PathVariable int userId, @PathVariable int buildingId) {
        service.addExistingBuildingToExistingUser(userId, buildingId);
        return ResponseEntity.ok("The building with id:" + buildingId + " is added to user with id" + userId);
    }

    @PutMapping(value = "/user/{userId}/building")
    public ResponseEntity<String> addNewBuildingToExistingUser(@PathVariable int userId, @RequestBody BuildingRequest buildingRequest) {
        service.addNewBuildingToExistingUser(userId, buildingRequest);
        return ResponseEntity.ok("The new building is added to user with id" + userId);
    }

    @DeleteMapping(value = "/building/{id}")
    public ResponseEntity<String> deleteBuildingByIdWithCondition(@PathVariable int id) {
        service.deleteBuildingById(id);
        return ResponseEntity.ok("The building is deleted");
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        service.deleteUserById(id);
        return ResponseEntity.ok("The user is deleted");
    }



}
