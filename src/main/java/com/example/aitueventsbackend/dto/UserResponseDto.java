package com.example.aitueventsbackend.dto;

import com.example.aitueventsbackend.model.Role;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.UUID;

@Schema(description = "User response payload")
public record UserResponseDto(
        @Schema(description = "User identifier", example = "123e4567-e89b-12d3-a456-426614174000")
        UUID id,
        @Schema(description = "Telegram user identifier", example = "123456789")
        Long telegramId,
        @Schema(description = "Telegram username", example = "aitu_student")
        String username,
        @Schema(description = "User first name", example = "Aruzhan")
        String firstName,
        @Schema(description = "User last name", example = "Sadykova")
        String lastName,
        @Schema(description = "Application role", example = "USER")
        Role role,
        @Schema(description = "User creation timestamp", example = "2026-04-20T17:30:00Z")
        Instant createdAt
) {
}
