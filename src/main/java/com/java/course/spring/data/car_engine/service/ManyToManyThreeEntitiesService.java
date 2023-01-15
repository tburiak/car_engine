package com.java.course.spring.data.car_engine.service;

import com.java.course.spring.data.car_engine.model.BuildingRequest;
import com.java.course.spring.data.car_engine.persistence.entity.BuildingEntity;
import com.java.course.spring.data.car_engine.persistence.entity.UserEntity;
import com.java.course.spring.data.car_engine.persistence.entity.UserRegistrationEntity;
import com.java.course.spring.data.car_engine.persistence.repository.BuildingRepository;
import com.java.course.spring.data.car_engine.persistence.repository.RegistrationRepository;
import com.java.course.spring.data.car_engine.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ManyToManyThreeEntitiesService {
    private final BuildingRepository buildingRepository;
    private final UserRepository userRepository;
    private final RegistrationRepository registrationRepository;

    public void assignNewUserAndNewBuilding(BuildingRequest buildingRequest) {
        var user = UserEntity.builder().build();
        userRepository.save(user);
        var building = BuildingEntity.builder().address(buildingRequest.getAddress()).build();
        buildingRepository.save(building);
        var registration = UserRegistrationEntity.builder()
                .user(user)
                .building(building)
                .registeredAt(LocalDateTime.now())
                .build();
        registrationRepository.save(registration);
    }
}
