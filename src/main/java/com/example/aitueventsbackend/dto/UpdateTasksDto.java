package com.example.aitueventsbackend.dto;

import com.example.aitueventsbackend.model.TasksStatus;
import com.example.aitueventsbackend.model.TasksStatus;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record UpdateTasksDto(
        @NotBlank(message = "Title cannot be empty")
        String title,
        @NotBlank(message = "Description cannot be empty")
        String description,
        @NotNull(message = "Status is required")
        TasksStatus status,
        @NotNull(message = "title cannot be empty")
        LocalDate created_at,
        @NotNull(message = "deadline cannot be empty")
        LocalDate deadline
) {

}