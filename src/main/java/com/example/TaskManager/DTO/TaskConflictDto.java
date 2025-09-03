package com.example.TaskManager.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskConflictDto {

    private UUID taskId;
    private TaskDto serverVersion;
}

