package com.java.course.spring.data.car_engine.controller;

import com.java.course.spring.data.car_engine.persistence.entity.OrderEntity;
import com.java.course.spring.data.car_engine.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/orders", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping
    public List<OrderEntity> getOrders() {
        return ordersService.getOrders();
    }

    @PostMapping
    public ResponseEntity<String> createOrder() {
        ordersService.createOrder();
        return ResponseEntity.ok("The order is created");
    }

    @DeleteMapping(value = "/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable int orderId) {
        ordersService.deleteOrder(orderId);
        return ResponseEntity.ok("The order is deleted");
    }

}
