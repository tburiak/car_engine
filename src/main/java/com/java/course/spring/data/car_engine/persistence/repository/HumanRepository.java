package com.java.course.spring.data.car_engine.persistence.repository;

import com.java.course.spring.data.car_engine.persistence.entity.HumanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HumanRepository extends JpaRepository<HumanEntity, Integer> {

    @Query(nativeQuery = true, value =
            "SELECT *\n" +
            "FROM car_engine.human h LEFT JOIN car_engine.car c ON h.human_id = c.human_id \n" +
            "WHERE c.car_id IS NULL")
    List<HumanEntity> findAllHumanWithoutCar();
}
