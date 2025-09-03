package com.example.TaskManager.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

    @NotNull(message = "Task ID cannot be null")
    private UUID id;

    @NotBlank(message = "Task title cannot be blank")
    private String title;

    private String description;

    @NotNull(message = "Completed status cannot be null")
    private Boolean isCompleted;

    @NotNull(message = "isDeleted status cannot be null")
    private Boolean isDeleted;

    @NotNull(message = "createdAt timestamp cannot be null")
    private Instant createdAt;

    @NotNull(message = "updatedAt timestamp cannot be null")
    private Instant updatedAt;

    public TaskDto(UUID id, String title, String description, Boolean isCompleted,
                   Instant createdAt, Instant updatedAt, Boolean isDeleted) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isCompleted = isCompleted;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isDeleted = isDeleted;
    }
}


