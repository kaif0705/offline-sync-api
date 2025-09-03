package com.example.TaskManager.Service;

import com.example.TaskManager.DTO.TaskDto;
import com.example.TaskManager.Exception.ResourceNotFoundException;
import com.example.TaskManager.Model.Task;
import com.example.TaskManager.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional(readOnly = true)
    public List<TaskDto> getAllTasks() {
        return taskRepository.findAllByIsDeletedFalse().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TaskDto getTaskById(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
        return mapToDto(task);
    }

    @Transactional
    public TaskDto createTask(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId() != null ? taskDto.getId() : UUID.randomUUID()); // Allow client-generated UUID
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setIsCompleted(taskDto.getIsCompleted());
        task.setCreatedAt(Instant.now());
        task.setUpdatedAt(Instant.now());
        task.setIsDeleted(false);

        Task savedTask = taskRepository.save(task);
        return mapToDto(savedTask);
    }

    @Transactional
    public TaskDto updateTask(UUID id, TaskDto taskDto) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        existingTask.setTitle(taskDto.getTitle());
        existingTask.setDescription(taskDto.getDescription());
        existingTask.setIsCompleted(taskDto.getIsCompleted());
        existingTask.setUpdatedAt(Instant.now()); // Update the timestamp

        Task updatedTask = taskRepository.save(existingTask);
        return mapToDto(updatedTask);
    }

    @Transactional
    public void deleteTask(UUID id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

        task.setIsDeleted(true);
        task.setUpdatedAt(Instant.now()); // Record the time of deletion
        taskRepository.save(task);
    }


    // --- Helper Methods for DTO/Entity Mapping ---
    private TaskDto mapToDto(Task task) {
        return new TaskDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getIsCompleted(),
                task.getCreatedAt(),
                task.getUpdatedAt(),
                task.getIsDeleted()
        );
    }
}
