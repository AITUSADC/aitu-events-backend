package com.example.aitueventsbackend.controller;

import com.example.aitueventsbackend.dto.UserCreateDto;
import com.example.aitueventsbackend.dto.UserFullUpdateDto;
import com.example.aitueventsbackend.dto.UserResponseDto;
import com.example.aitueventsbackend.dto.UserUpdateDto;
import com.example.aitueventsbackend.model.User;
import com.example.aitueventsbackend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @PostMapping
    public UserResponseDto create(@Valid @RequestBody UserCreateDto createDto) {
        return toResponseDto(
                userService.create(
                        createDto.telegramId(),
                        createDto.username(),
                        createDto.firstName(),
                        createDto.lastName()
                )
        );
    }

    @GetMapping
    public Page<UserResponseDto> getAllUsers(Pageable pageable) {
        return userService.getAll(pageable).map(this::toResponseDto);
    }

    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable UUID id) {
        return toResponseDto(userService.getById(id));
    }

    @PutMapping("/{id}")
    public UserResponseDto fullUpdateUser(@PathVariable UUID id, @Valid @RequestBody UserFullUpdateDto fullUpdateDto) {
        return toResponseDto(
                userService.fullUpdate(
                        id,
                        fullUpdateDto.telegramId(),
                        fullUpdateDto.username(),
                        fullUpdateDto.firstName(),
                        fullUpdateDto.lastName(),
                        fullUpdateDto.role()
                )
        );
    }

    @PatchMapping("/{id}")
    public UserResponseDto partialUpdateUser(@PathVariable UUID id, @RequestBody UserUpdateDto updateDto) {
        return toResponseDto(
                userService.partialUpdate(
                        id,
                        updateDto.telegramId(),
                        updateDto.username(),
                        updateDto.firstName(),
                        updateDto.lastName(),
                        updateDto.role()
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private UserResponseDto toResponseDto(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getTelegramId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getCreatedAt()
        );
    }
}
