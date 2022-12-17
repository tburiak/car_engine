package com.java.course.spring.data.car_engine;

import com.java.course.spring.data.car_engine.persistence.entity.CarEntity;
import com.java.course.spring.data.car_engine.persistence.entity.EngineEntity;
import com.java.course.spring.data.car_engine.persistence.entity.HumanEntity;
import com.java.course.spring.data.car_engine.persistence.repository.CarRepository;
import com.java.course.spring.data.car_engine.persistence.repository.EngineRepository;
import com.java.course.spring.data.car_engine.persistence.repository.HumanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class CarEngineApplication implements CommandLineRunner {
    @Autowired
    HumanRepository humanRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    EngineRepository engineRepository;

    public static void main(String[] args) {
        SpringApplication.run(CarEngineApplication.class, args);
    }

    @Override
    public void run(String... args) {
        //TASK 1

        log.info("Input initial data to database");
        int count = 5;
        List<EngineEntity> engineEntities = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            engineEntities.add(EngineEntity.builder().title("Engine_" + UUID.randomUUID()).build());
        }
        engineRepository.saveAll(engineEntities);

        List<CarEntity> carEntities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            carEntities.add(CarEntity.builder().title("Car_" + UUID.randomUUID()).build());
        }
        carRepository.saveAll(carEntities);

        //TASK 2

        List<HumanEntity> humanEntities = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            humanEntities.add(HumanEntity.builder().firstName("Name_" + UUID.randomUUID()).build());
        }

        humanRepository.saveAll(humanEntities);


    }
}
