package com.java.course.spring.data.car_engine.service;

import com.java.course.spring.data.car_engine.model.TaskRequest;
import com.java.course.spring.data.car_engine.persistence.entity.OrderEntity;
import com.java.course.spring.data.car_engine.persistence.entity.TaskEntity;
import com.java.course.spring.data.car_engine.persistence.repository.OrdersRepository;
import com.java.course.spring.data.car_engine.persistence.repository.TasksRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TasksService {
    private final OrdersRepository ordersRepository;
    public static final String DONE = "done";
    private final TasksRepository tasksRepository;

    public List<TaskEntity> getTasks() {
        return tasksRepository.findAll();
    }

    public void createTask(TaskRequest taskRequest) {
        var parent = tasksRepository.findById(taskRequest.getParentId()).orElse(null);
        var task = TaskEntity.builder().parent(parent).build();
        tasksRepository.save(task);
    }

    @Transactional
    public void updateTask(int taskId, TaskRequest taskRequest) {
        var taskToUpdate = tasksRepository.getReferenceById(taskId);
        var parent = tasksRepository.findById(taskRequest.getParentId()).orElseThrow();
        taskToUpdate.setParent(parent);
        completeTaskWithSubtasks(taskToUpdate);
        tasksRepository.save(taskToUpdate);
    }

    @Transactional
    public void completeTask(int taskId) {
        var taskToUpdate = tasksRepository.getReferenceById(taskId);
        completeTaskWithSubtasks(taskToUpdate);
        tasksRepository.save(taskToUpdate);
    }

    @Transactional
    public void deleteTask(int taskId) {
        var taskToDelete = tasksRepository.getReferenceById(taskId);
        var orderTasks = taskToDelete.getOrder().getTasks();
        orderTasks.removeIf(task -> task.getId() == taskId);
        if(orderTasks.size() == 1) {
            taskToDelete.getOrder().setStatus(DONE);
        }
        deleteTaskWithSubtasks(taskToDelete);
    }

    @Transactional
    public void addToOrder(int taskId, int orderId) {
        var task = tasksRepository.getReferenceById(taskId);
        var order = ordersRepository.getReferenceById(orderId);
        addTaskWithSubtasksToOrder(task, order);
        tasksRepository.save(task);
    }

    private void deleteTaskWithSubtasks(TaskEntity task) {
        var subtasks = task.getSubtasks();
        if(!subtasks.isEmpty()){
            for(TaskEntity subtask : subtasks) {
                deleteTaskWithSubtasks(subtask);
            }
        }
        tasksRepository.deleteById(task.getId());
    }

    private void completeTaskWithSubtasks(TaskEntity task) {
        var subtasks = task.getSubtasks();
        if(!subtasks.isEmpty()) {
            for(TaskEntity subtask : subtasks) {
                completeTaskWithSubtasks(subtask);
            }
        }
        task.setStatus(DONE);
    }

    private void addTaskWithSubtasksToOrder(TaskEntity task, OrderEntity orderEntity) {
        var subtasks = task.getSubtasks();
        if(!subtasks.isEmpty()) {
            for(TaskEntity subtask : subtasks) {
                addTaskWithSubtasksToOrder(subtask, orderEntity);
            }
        }
        task.setOrder(orderEntity);
    }

}
