package com.example.TaskManager.Service;

import com.example.TaskManager.DTO.SyncResponseDto;
import com.example.TaskManager.DTO.TaskConflictDto;
import com.example.TaskManager.DTO.TaskDto;
import com.example.TaskManager.Model.Task;
import com.example.TaskManager.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SyncServiceImpl implements SyncService {

    @Autowired
    private TaskRepository taskRepository;

    @Transactional
    public SyncResponseDto processSyncBatch(List<TaskDto> clientTasks) {
        List<TaskDto> syncedTasks = new ArrayList<>();
        List<TaskConflictDto> conflicts = new ArrayList<>();

        for (TaskDto clientTaskDto : clientTasks) {
            UUID taskId = clientTaskDto.getId();
            if (taskId == null) {
                Task createdTask = createNewTaskFromServer(clientTaskDto);
                syncedTasks.add(mapToDto(createdTask));
                continue;
            }

            Optional<Task> serverTaskOpt = taskRepository.findById(taskId);

            if (serverTaskOpt.isEmpty()) {
                Task createdTask = createNewTaskFromServer(clientTaskDto);
                syncedTasks.add(mapToDto(createdTask));
            } else {
                Task serverTask = serverTaskOpt.get();

                if (clientTaskDto.getUpdatedAt().isAfter(serverTask.getUpdatedAt())) {
                    Task updatedTask = updateServerTask(serverTask, clientTaskDto);
                    syncedTasks.add(mapToDto(updatedTask));
                } else {
                    conflicts.add(new TaskConflictDto(taskId, mapToDto(serverTask)));
                }
            }
        }

        return new SyncResponseDto(syncedTasks, conflicts);
    }

    public Task createNewTaskFromServer(TaskDto clientTaskDto) {
        Task newTask = new Task();
        newTask.setId(clientTaskDto.getId() != null ? clientTaskDto.getId() : UUID.randomUUID());
        newTask.setTitle(clientTaskDto.getTitle());
        newTask.setDescription(clientTaskDto.getDescription());
        newTask.setIsCompleted(clientTaskDto.getIsCompleted());
        newTask.setCreatedAt(clientTaskDto.getCreatedAt() != null ? clientTaskDto.getCreatedAt() : Instant.now());
        newTask.setUpdatedAt(clientTaskDto.getUpdatedAt() != null ? clientTaskDto.getUpdatedAt() : Instant.now());
        newTask.setIsDeleted(clientTaskDto.getIsDeleted() != null && clientTaskDto.getIsDeleted());
        return taskRepository.save(newTask);
    }

    public Task updateServerTask(Task serverTask, TaskDto clientTaskDto) {
        serverTask.setTitle(clientTaskDto.getTitle());
        serverTask.setDescription(clientTaskDto.getDescription());
        serverTask.setIsCompleted(clientTaskDto.getIsCompleted());
        serverTask.setUpdatedAt(clientTaskDto.getUpdatedAt()); // Use the client's authoritative timestamp
        serverTask.setIsDeleted(clientTaskDto.getIsDeleted() != null && clientTaskDto.getIsDeleted());
        return taskRepository.save(serverTask);
    }

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
