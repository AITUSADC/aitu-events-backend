package com.example.aitueventsbackend.dto;

import com.example.aitueventsbackend.model.Role;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Payload for partially updating a user")
public record UserUpdateDto(
        @Schema(description = "Telegram user identifier", example = "123456789", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Long telegramId,
        @Schema(description = "Telegram username if available", example = "aitu_student", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String username,
        @Schema(description = "User first name", example = "Aruzhan", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String firstName,
        @Schema(description = "User last name", example = "Sadykova", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        String lastName,
        @Schema(description = "Application role", example = "USER", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
        Role role
) {
}
