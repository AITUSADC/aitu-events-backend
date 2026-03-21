package com.example.aitueventsbackend.dto;

import com.example.aitueventsbackend.TasksStatus.TaskStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.UUID;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
@JsonPropertyOrder({"id", "title", "description", "status", "created_at", "deadline"})
public record TasksResponseDto(
        UUID id,
        String title,
        String description,
        TaskStatus status,
        LocalDate created_at,
        LocalDate deadline
) {


}