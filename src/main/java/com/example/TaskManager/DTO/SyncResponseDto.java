package com.example.TaskManager.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SyncResponseDto {

    private List<TaskDto> successfullySyncedTasks;
    private List<TaskConflictDto> conflictedTasks;
}
