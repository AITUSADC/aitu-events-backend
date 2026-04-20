package com.example.aitueventsbackend.controller;

import com.example.aitueventsbackend.dto.CreateTaskDto;
import com.example.aitueventsbackend.dto.TaskResponseDto;
import com.example.aitueventsbackend.dto.UpdateTaskDto;
import com.example.aitueventsbackend.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Operations for managing tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/paginated")
    @Operation(summary = "Get tasks with pagination")
    @ApiResponse(responseCode = "200", description = "Paginated list of tasks returned")
    public ResponseEntity<Page<TaskResponseDto>> getTasksPaginated(Pageable pageable) {
        return ResponseEntity.ok(taskService.getTasksPaginated(pageable));
    }

    @GetMapping
    @Operation(summary = "Get all tasks")
    @ApiResponse(responseCode = "200", description = "List of tasks returned")
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a task by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task returned"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable UUID id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new task")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Task created"),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })
    public ResponseEntity<TaskResponseDto> createTask(@Valid @RequestBody CreateTaskDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.createTask(dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task by id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Task deleted"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a task by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Task updated"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable UUID id, @Valid @RequestBody UpdateTaskDto dto) {
        return ResponseEntity.ok(taskService.updateTask(id, dto));
    }
}
