package com.java.course.spring.data.car_engine.service;

import com.java.course.spring.data.car_engine.model.BuildingRequest;
import com.java.course.spring.data.car_engine.persistence.entity.Building1Entity;
import com.java.course.spring.data.car_engine.persistence.entity.User1Entity;
import com.java.course.spring.data.car_engine.persistence.repository.Building1Repository;
import com.java.course.spring.data.car_engine.persistence.repository.User1Repository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManyToManyTwoEntitiesService {
    private final Building1Repository building1Repository;
    private final User1Repository user1Repository;


    public List<Building1Entity> getBuildings() {
        return building1Repository.findAll();
    }

    public void createBuilding(BuildingRequest buildingRequest) {
        Building1Entity building1Entity = Building1Entity.builder()
                .address(buildingRequest.getAddress()).build();
        building1Repository.save(building1Entity);
    }

    public List<User1Entity> getUsers() {
        return user1Repository.findAll();
    }

    public void createUser() {
        user1Repository.save(User1Entity.builder().build());
    }

    @Transactional
    public void updateBuildingById(int buildingId, BuildingRequest buildingRequest) {
        var buildingToUpdate = building1Repository.getReferenceById(buildingId);
        buildingToUpdate.setAddress(buildingRequest.getAddress());
        building1Repository.save(buildingToUpdate); //optional
    }

    @Transactional
    public void addNewBuildingToExistingUser(int userId, BuildingRequest buildingRequest) {
        var userToUpdate = user1Repository.getReferenceById(userId);
        var buildingToAdd = Building1Entity.builder().address(buildingRequest.getAddress()).build();
        building1Repository.save(buildingToAdd);
        userToUpdate.getBuildings().add(buildingToAdd);
        user1Repository.save(userToUpdate); //optional
    }

    @Transactional
    public void addExistingBuildingToExistingUser(int userId, int buildingId) {
        var userToUpdate = user1Repository.getReferenceById(userId);
        var buildingToAdd = building1Repository.getReferenceById(buildingId);
        userToUpdate.getBuildings().add(buildingToAdd);
        user1Repository.save(userToUpdate); //optional
    }

    @Transactional
    public void deleteBuildingById(int id) {
        var buildingToDelete = building1Repository.getReferenceById(id);
        var buildingUsers = buildingToDelete.getUsers();
        buildingUsers.forEach(user1Entity -> {
                    user1Entity.getBuildings().removeIf(building1Entity ->
                            building1Entity.getId() == buildingToDelete.getId()
                    );
                    if (user1Entity.getBuildings().isEmpty()) {
                        user1Repository.delete(user1Entity);
                    }
                }
        );
        building1Repository.deleteById(id);
    }

    @Transactional
    public void deleteUserById(int id) {
        var userToDelete = user1Repository.getReferenceById(id);
        userToDelete.getBuildings().forEach(building1Entity -> building1Entity.getUsers()
                .removeIf(user1Entity ->
                        user1Entity.getId() == userToDelete.getId()
                )
        );
        user1Repository.deleteById(id);
    }
}
