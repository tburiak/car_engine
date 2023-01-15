package com.java.course.spring.data.car_engine.persistence.repository;

import com.java.course.spring.data.car_engine.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<OrderEntity, Integer> {
}
