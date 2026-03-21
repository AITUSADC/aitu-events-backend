package com.example.aitueventsbackend.controller;

import com.example.aitueventsbackend.dto.UserCreateDto;
import com.example.aitueventsbackend.dto.UserResponseDto;
import com.example.aitueventsbackend.dto.UserUpdateDto;
import com.example.aitueventsbackend.model.User;
import com.example.aitueventsbackend.services.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    // Create user
    @PostMapping
    public UserResponseDto create(@Valid @RequestBody UserCreateDto createDto) {
        return toResponseDto(
                userService.create(
                        createDto.telegramId(),
                        createDto.username(),
                        createDto.firstName(),
                        createDto.lastName(),
                        createDto.role()
                )
        );
    }

    // Get all users
    @GetMapping
    public Page<UserResponseDto> getAllUsers(Pageable pageable) {
        return userService.getAll(pageable).map(user -> toResponseDto(user));
    }

    // Get one user by id
    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return toResponseDto(userService.getById(id));
    }

    // Update all fields of user
    @PutMapping("/{id}")
    public UserResponseDto fullUpdateUser(@PathVariable Long id, @Valid @RequestBody UserCreateDto createDto) {
        return toResponseDto(
                userService.fullUpdate(
                        id,
                        createDto.telegramId(),
                        createDto.username(),
                        createDto.firstName(),
                        createDto.lastName(),
                        createDto.role()
                )
        );
    }

    // Update part of user
    @PatchMapping("/{id}")
    public UserResponseDto partialUpdateUser(@PathVariable Long id, @RequestBody UserUpdateDto updateDto) {
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

    // Delete User
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
         userService.delete(id);
         return "User deleted successfully.";
    }

    // method to convert user to response dto
    public UserResponseDto toResponseDto (User user) {
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
