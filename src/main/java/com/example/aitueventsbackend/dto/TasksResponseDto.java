package com.example.aitueventsbackend.dto;

import com.example.aitueventsbackend.model.TasksStatus;
import com.example.aitueventsbackend.model.TasksStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.UUID;

import java.time.LocalDate;
@JsonPropertyOrder({"id", "title", "description", "status", "created_at", "deadline"})
public record TasksResponseDto(
        UUID id,
        String title,
        String description,
        TasksStatus status,
        LocalDate created_at,
        LocalDate deadline
) {


}