package com.example.aitueventsbackend.controller;
import com.example.aitueventsbackend.taskService.TasksService;
import com.example.aitueventsbackend.dto.CreateTasksDto;
import com.example.aitueventsbackend.dto.TasksResponseDto;
import com.example.aitueventsbackend.dto.UpdateTasksDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TasksController {

    private static final Logger log = LoggerFactory.getLogger(TasksController.class);
    private final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<TasksResponseDto> getTaskById(@PathVariable UUID id) {
        log.info("Called getTaskById with id={}", id);
        return ResponseEntity.ok(tasksService.getTasksById(id));
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<TasksResponseDto>> getTasksPaginated(Pageable pageable) {
        log.info("Called getTasksPaginated");
        return ResponseEntity.ok(tasksService.getTasksPaginated(pageable));
    }

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<List<TasksResponseDto>> getAllTasks() {
        log.info("Called getAllTasks");
        return ResponseEntity.ok(tasksService.getAllTasks());
    }

    // ✅ CREATE
    @PostMapping
    public ResponseEntity<TasksResponseDto> createTask(
            @Valid @RequestBody CreateTasksDto dto
    ) {
        log.info("Called createTask");
        return new ResponseEntity<>(tasksService.createTask(dto), HttpStatus.CREATED);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        log.info("Called deleteTask with id={}", id);
        tasksService.deleteTaskById(id); // ✅
        return ResponseEntity.noContent().build(); // 204 No Content
    }
    // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<TasksResponseDto> updateTask(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateTasksDto dto
    ) {
        TasksResponseDto updated = tasksService.updateTask(id, dto);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }
}