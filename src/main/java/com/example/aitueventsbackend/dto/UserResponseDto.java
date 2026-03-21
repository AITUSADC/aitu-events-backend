package com.example.aitueventsbackend.dto;

import com.example.aitueventsbackend.model.Role;

import java.time.Instant;

public record UserResponseDto(
        Long id,
        Long telegramId,
        String username,
        String firstName,
        String lastName,
        Role role,
        Instant createdAt
) {
}
