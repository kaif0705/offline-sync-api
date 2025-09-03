package com.example.TaskManager.Service;

import com.example.TaskManager.DTO.SyncResponseDto;
import com.example.TaskManager.DTO.TaskDto;
import com.example.TaskManager.Model.Task;

import java.util.List;

public interface SyncService {

    SyncResponseDto processSyncBatch(List<TaskDto> clientTasks);
    Task createNewTaskFromServer(TaskDto clientTaskDto);
    Task updateServerTask(Task serverTask, TaskDto clientTaskDto);


}
