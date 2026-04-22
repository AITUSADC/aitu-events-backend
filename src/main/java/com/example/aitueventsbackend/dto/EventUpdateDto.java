package com.example.aitueventsbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "Payload for partially updating a user")
public record EventUpdateDto (

        @Schema(description = "Event title", example = "Welcome Day")
        String title,

        @Schema(description = "Event description", example = "Come to Welcome Day to meet our university!")
        String description,

        @Schema(description = "Event date and time", example = "2026-05-14")
        Instant eventDate,

        @Schema(description = "Event location", example = "Open Space")
        String location

 ) {}
