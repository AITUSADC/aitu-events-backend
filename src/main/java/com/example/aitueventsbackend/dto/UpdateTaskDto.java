package com.example.aitueventsbackend.dto;

import com.example.aitueventsbackend.model.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Schema(description = "Payload for fully updating an existing task")
public record UpdateTaskDto(
        @Schema(description = "Updated task title", example = "Publish final event agenda")
        @NotBlank(message = "Title cannot be empty")
        String title,
        @Schema(description = "Updated task description", example = "Publish the approved agenda to all participants")
        @NotBlank(message = "Description cannot be empty")
        String description,
        @Schema(description = "Updated task status", example = "COMPLETED")
        @NotNull(message = "Status is required")
        TaskStatus status,
        @Schema(description = "Updated task deadline in ISO date format", example = "2026-05-02")
        @NotNull(message = "Deadline cannot be empty")
        LocalDate deadline
) {
}
