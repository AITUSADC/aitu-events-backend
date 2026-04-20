package com.example.aitueventsbackend.dto;

import com.example.aitueventsbackend.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateTaskDto(
        @NotBlank(message = "Title cannot be empty")
        String title,
        @NotBlank(message = "Description cannot be empty")
        String description,
        @NotNull(message = "Status is required")
        TaskStatus status,
        @NotNull(message = "Deadline cannot be empty")
        LocalDate deadline
) {
}
