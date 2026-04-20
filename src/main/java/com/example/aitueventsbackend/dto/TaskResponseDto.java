package com.example.aitueventsbackend.dto;

import com.example.aitueventsbackend.model.TaskStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
import java.util.UUID;

@JsonPropertyOrder({"id", "title", "description", "status", "createdAt", "deadline"})
public record TaskResponseDto(
        UUID id,
        String title,
        String description,
        TaskStatus status,
        LocalDate createdAt,
        LocalDate deadline
) {
}
