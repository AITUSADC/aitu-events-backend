package com.example.aitueventsbackend.dto;

import com.example.aitueventsbackend.model.Role;

import java.time.Instant;
import java.util.UUID;

public record UserResponseDto(
        UUID id,
        Long telegramId,
        String username,
        String firstName,
        String lastName,
        Role role,
        Instant createdAt
) {
}
