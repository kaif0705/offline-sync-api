package com.example.TaskManager.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
public class TaskDto {

    @NotNull(message = "Task ID cannot be null")
    private UUID id;

    @NotBlank(message = "Task title cannot be blank")
    private String title;

    private String description;

    @NotNull(message = "Completed status cannot be null")
    private Boolean completed;

    @NotNull(message = "isDeleted status cannot be null")
    private Boolean isDeleted;

    @NotNull(message = "updatedAt timestamp cannot be null")
    private Instant updatedAt;
}


