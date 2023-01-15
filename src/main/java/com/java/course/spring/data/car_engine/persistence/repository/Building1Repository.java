package com.java.course.spring.data.car_engine.persistence.repository;

import com.java.course.spring.data.car_engine.persistence.entity.Building1Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Building1Repository extends JpaRepository<Building1Entity, Integer> {


}
