package com.example.aitueventsbackend.dto;

import com.example.aitueventsbackend.TasksStatus.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record CreateTasksDto(
        @NotBlank(message = "Title cannot be empty")
        String title,
        @NotBlank(message = "Description cannot be empty")
        String description,
        @NotNull(message = "Status is required")
        TaskStatus status,
        @NotNull
        LocalDate created_at,
        @NotNull(message = "deadline cannot be empty")
        LocalDate deadline
) {

}