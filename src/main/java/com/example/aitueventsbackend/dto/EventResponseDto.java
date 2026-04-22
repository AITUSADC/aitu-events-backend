package com.example.aitueventsbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.UUID;

@Schema(description = "Event response payload")
public record EventResponseDto (
        @Schema(description = "Event identifier", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID id,

        @Schema(description = "Event title", example = "Welcome Day")
        String title,

        @Schema(description = "Event description", example = "Come to Welcome Day to meet our university!")
        String description,

        @Schema(description = "Event date and time", example = "2026-05-14")
        Instant eventDate,

        @Schema(description = "Event location", example = "Open Space")
        String location,

        @Schema(description = "Event creation date", example = "2026-04-30")
        Instant createdAt

) {

}


