package com.example.aitueventsbackend.dto;

import com.example.aitueventsbackend.model.TaskStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.UUID;

@JsonPropertyOrder({"id", "title", "description", "status", "createdAt", "deadline"})
@Schema(description = "Task response payload")
public record TaskResponseDto(
        @Schema(description = "Task identifier", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID id,
        @Schema(description = "Task title", example = "Prepare event agenda")
        String title,
        @Schema(description = "Task description", example = "Collect speaker topics and finalize the agenda")
        String description,
        @Schema(description = "Task status", example = "IN_PROGRESS")
        TaskStatus status,
        @Schema(description = "Task creation date", example = "2026-04-20")
        LocalDate createdAt,
        @Schema(description = "Task deadline", example = "2026-04-30")
        LocalDate deadline
) {
}
