package com.example.aitueventsbackend.services;

import com.example.aitueventsbackend.dto.CreateTaskDto;
import com.example.aitueventsbackend.dto.TaskResponseDto;
import com.example.aitueventsbackend.dto.UpdateTaskDto;
import com.example.aitueventsbackend.exceptions.TaskNotFoundException;
import com.example.aitueventsbackend.model.Task;
import com.example.aitueventsbackend.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskResponseDto createTask(CreateTaskDto dto) {
        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setDeadline(dto.deadline());

        return mapToResponseDto(taskRepository.save(task));
    }

    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    public Page<TaskResponseDto> getTasksPaginated(Pageable pageable) {
        return taskRepository.findAll(pageable)
                .map(this::mapToResponseDto);
    }

    public TaskResponseDto getTaskById(UUID id) {
        return mapToResponseDto(findTaskById(id));
    }

    public void deleteTaskById(UUID id) {
        taskRepository.delete(findTaskById(id));
    }

    public TaskResponseDto updateTask(UUID id, UpdateTaskDto dto) {
        Task task = findTaskById(id);
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setDeadline(dto.deadline());

        return mapToResponseDto(taskRepository.save(task));
    }

    private Task findTaskById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    private TaskResponseDto mapToResponseDto(Task task) {
        return new TaskResponseDto(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedAt(),
                task.getDeadline()
        );
    }
}
