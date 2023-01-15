package com.java.course.spring.data.car_engine.controller;

import com.java.course.spring.data.car_engine.model.TaskRequest;
import com.java.course.spring.data.car_engine.persistence.entity.TaskEntity;
import com.java.course.spring.data.car_engine.service.TasksService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/tasks", produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Slf4j
public class TasksController {

    private final TasksService tasksService;

    @GetMapping
    public List<TaskEntity> getTasks() {
        return tasksService.getTasks();
    }

    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody TaskRequest taskRequest) {
        tasksService.createTask(taskRequest);
        return ResponseEntity.ok("Task is created");
    }

    @PutMapping(value = "/{taskId}")
    public ResponseEntity<String> updateTask(@PathVariable int taskId, @RequestBody TaskRequest taskRequest) {
        tasksService.updateTask(taskId, taskRequest);
        return ResponseEntity.ok("The task is updated");
    }

    @PutMapping(value = "/{taskId}/status")
    public ResponseEntity<String> completeTask(@PathVariable int taskId) {
        tasksService.completeTask(taskId);
        return ResponseEntity.ok("The task is completed");
    }

    @PutMapping(value = "/{taskId}/order/{orderId}")
    public ResponseEntity<String> completeTask(@PathVariable int taskId, @PathVariable int orderId) {
        tasksService.addToOrder(taskId, orderId);
        return ResponseEntity.ok("The task is completed");
    }

    @DeleteMapping(value = "/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable int taskId) {
        tasksService.deleteTask(taskId);
        return ResponseEntity.ok("The task is deleted");
    }

}
