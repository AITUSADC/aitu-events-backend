package com.example.aitueventsbackend.controller;

import com.example.aitueventsbackend.dto.UserCreateDto;
import com.example.aitueventsbackend.dto.UserFullUpdateDto;
import com.example.aitueventsbackend.dto.UserResponseDto;
import com.example.aitueventsbackend.dto.UserUpdateDto;
import com.example.aitueventsbackend.model.User;
import com.example.aitueventsbackend.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Operations for managing users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @PostMapping
    @Operation(summary = "Create a new user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Validation error or duplicate telegram id")
    })
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
    @Operation(summary = "Get users with pagination")
    @ApiResponse(responseCode = "200", description = "Paginated list of users returned")
    public Page<UserResponseDto> getAllUsers(Pageable pageable) {
        return userService.getAll(pageable).map(this::toResponseDto);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User returned"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public UserResponseDto getUserById(@PathVariable UUID id) {
        return toResponseDto(userService.getById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Replace a user by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "400", description = "Validation error or duplicate telegram id"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
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
    @Operation(summary = "Partially update a user by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "400", description = "Invalid update payload or duplicate telegram id"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
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
    @Operation(summary = "Delete a user by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
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
