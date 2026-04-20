package com.example.aitueventsbackend.dto;

import com.example.aitueventsbackend.model.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "Payload for creating a new task")
public record CreateTaskDto(
        @Schema(description = "Short task title", example = "Prepare event agenda")
        @NotBlank(message = "Title cannot be empty")
        String title,
        @Schema(description = "Detailed task description", example = "Collect speaker topics and finalize the agenda")
        @NotBlank(message = "Description cannot be empty")
        String description,
        @Schema(description = "Current task status", example = "IN_PROGRESS")
        @NotNull(message = "Status is required")
        TaskStatus status,
        @Schema(description = "Task deadline in ISO date format", example = "2026-04-30")
        @NotNull(message = "Deadline cannot be empty")
        LocalDate deadline
) {
}
