package com.example.TaskManager.DTO;

import com.example.TaskManager.Model.Task;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SyncResponseDto {

    private List<Task> successfullySyncedTasks;
    private List<TaskConflictDto> conflictedTasks;
}
