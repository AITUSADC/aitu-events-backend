package com.example.aitueventsbackend.TasksService;

import com.example.aitueventsbackend.Exceptions.TaskNotFoundException;
import com.example.aitueventsbackend.Model.Tasks;
import com.example.aitueventsbackend.dto.CreateTasksDto;
import com.example.aitueventsbackend.dto.TasksResponseDto;
import com.example.aitueventsbackend.dto.UpdateTasksDto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TasksService {

    private final Map<UUID, Tasks> tasksMap = new HashMap<>();

    // CREATE
    public TasksResponseDto createTask(CreateTasksDto dto) {
        UUID id = UUID.randomUUID();
        Tasks task = mapToEntity(id, dto);
        tasksMap.put(id, task);
        return mapToResponseDto(task);
    }

    // GET ALL
    public List<TasksResponseDto> getAllTasks() {
        List<TasksResponseDto> result = new ArrayList<>();
        for (Tasks task : tasksMap.values()) {
            result.add(mapToResponseDto(task));
        }
        return result;
    }

    // GET BY ID
    public TasksResponseDto getTasksById(UUID id) {
        Tasks task = tasksMap.get(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        return mapToResponseDto(task);
    }

    // DELETE
    public void deleteTaskById(UUID id) {
        if (!tasksMap.containsKey(id)) {
            throw new TaskNotFoundException(id);
        }
        tasksMap.remove(id);
    }

    // UPDATE
    public TasksResponseDto updateTask(UUID id, UpdateTasksDto dto) {
        Tasks existingTask = tasksMap.get(id);
        if (existingTask == null) {
            throw new TaskNotFoundException(id);
        }
        Tasks updatedTask = mapToEntity(id, dto);
        tasksMap.put(id, updatedTask);
        return mapToResponseDto(updatedTask);
    }

    // Mapping DTOs to Entities
    private Tasks mapToEntity(UUID id, CreateTasksDto dto) {
        return new Tasks(id, dto.title(), dto.description(), dto.status(), dto.created_at(), dto.deadline());
    }

    private Tasks mapToEntity(UUID id, UpdateTasksDto dto) {
        return new Tasks(id, dto.title(), dto.description(),dto.status(), dto.created_at(), dto.deadline());
    }

    private TasksResponseDto mapToResponseDto(Tasks task) {
        return new TasksResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreated_at(),
                task.getDeadline()
        );

    }
}