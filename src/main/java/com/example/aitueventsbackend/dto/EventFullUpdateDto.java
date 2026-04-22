package com.example.aitueventsbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;


@Schema(description = "Payload for fully updating a user")
public record EventFullUpdateDto (

    @Schema(description = "Event title", example = "Welcome day")
    @NotBlank(message = "Title can not be empty")
    String title,

    @Schema(description = "Event description", example = "Come to Welcome Day to meet our university!")
    String description,

    @Schema(description = "Event date and time", example = "2026-05-14")
    @NotNull(message = "Date and time are required")
    Instant eventDate,

    @Schema(description = "Event location", example = "Open Space")
    @NotBlank(message = "Location can not be empty")
    String location
) {}
