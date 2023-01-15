package com.java.course.spring.data.car_engine.service;

import com.java.course.spring.data.car_engine.persistence.entity.OrderEntity;
import com.java.course.spring.data.car_engine.persistence.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersService {
    private final OrdersRepository ordersRepository;


    public List<OrderEntity> getOrders() {
        return ordersRepository.findAll();
    }

    public void createOrder() {
        ordersRepository.save(new OrderEntity());
    }

    @Transactional
    public void deleteOrder(int orderId) {
        var orderToDelete = ordersRepository.getReferenceById(orderId);
        orderToDelete.getTasks().forEach(task -> task.setOrder(null));
        ordersRepository.deleteById(orderId);
    }
}
