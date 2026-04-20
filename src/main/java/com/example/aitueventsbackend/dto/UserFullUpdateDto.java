package com.example.aitueventsbackend.dto;

import com.example.aitueventsbackend.model.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Payload for fully updating a user")
public record UserFullUpdateDto(
        @Schema(description = "Telegram user identifier", example = "123456789")
        @NotNull
        Long telegramId,

        @Schema(description = "Telegram username if available", example = "aitu_student", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String username,

        @Schema(description = "User first name", example = "Aruzhan")
        @NotBlank
        String firstName,

        @Schema(description = "User last name", example = "Sadykova")
        @NotBlank
        String lastName,

        @Schema(description = "Application role", example = "ADMIN")
        @NotNull
        Role role
) {
}
