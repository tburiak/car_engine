package com.java.course.spring.data.car_engine.persistence.repository;

import com.java.course.spring.data.car_engine.persistence.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Integer> {
    Optional<CarEntity> findByEngineEntityId(int engineId);

    @Query(nativeQuery = true, value = "SELECT * FROM car c WHERE c.car_engine_id is NULL")
    List<CarEntity> findNoEngineCar();
}
