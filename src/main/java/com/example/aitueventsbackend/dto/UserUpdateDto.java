package com.example.aitueventsbackend.dto;

import com.example.aitueventsbackend.model.Role;

public record UserUpdateDto(
        Long telegramId,
        String username,
        String firstName,
        String lastName,
        Role role
) {
}
