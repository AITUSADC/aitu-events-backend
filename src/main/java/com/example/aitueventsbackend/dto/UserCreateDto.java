package com.example.aitueventsbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreateDto(
        @NotNull
        Long telegramId,

        // username is optional
        String username,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName

) {
}
