package com.example.TaskManager.Service;

import com.example.TaskManager.DTO.TaskDto;

import java.util.List;
import java.util.UUID;

public interface TaskService {

    List<TaskDto> getAllTasks();
    TaskDto getTaskById(UUID id);
    TaskDto createTask(TaskDto taskDto);
    TaskDto updateTask(UUID id, TaskDto taskDto);
    void deleteTask(UUID id);

}
