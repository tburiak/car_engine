package com.java.course.spring.data.car_engine.persistence.repository;

import com.java.course.spring.data.car_engine.persistence.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Integer> {
    Optional<CarEntity> findByEngineEntityId(int engineId);

    List<CarEntity> findByEngineEntityIsNull();
}
