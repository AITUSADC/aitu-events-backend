package com.example.aitueventsbackend.dto;

import com.example.aitueventsbackend.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCreateDto(
        @NotNull
        Long telegramId,

        @NotBlank
        String username,

        @NotBlank
        String firstName,

        @NotBlank
        String lastName,

        @NotNull
        Role role
) {
}
