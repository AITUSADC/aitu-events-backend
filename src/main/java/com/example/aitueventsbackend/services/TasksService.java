package com.example.aitueventsbackend.services;
import com.example.aitueventsbackend.exceptions.TasksNotFoundException;
import com.example.aitueventsbackend.model.Tasks;
import com.example.aitueventsbackend.dto.CreateTasksDto;
import com.example.aitueventsbackend.dto.TasksResponseDto;
import com.example.aitueventsbackend.dto.UpdateTasksDto;
import com.example.aitueventsbackend.repository.TasksRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

@Service
public class TasksService {
    private final TasksRepository tasksRepository;

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public TasksResponseDto createTask(CreateTasksDto dto) {
        Tasks task = new Tasks();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setCreated_at(dto.created_at());
        task.setDeadline(dto.deadline());

        task = tasksRepository.save(task); // save to DB
        return mapToResponseDto(task);
    }

    // GET ALL
    public List<TasksResponseDto> getAllTasks() {
        List<Tasks> tasks = tasksRepository.findAll();
        return tasks.stream()
                .map(this::mapToResponseDto)
                .toList();
    }

    public Page<TasksResponseDto> getTasksPaginated(Pageable pageable) {
        return tasksRepository.findAll(pageable)
                .map(this::mapToResponseDto);
    }


    // GET BY ID
    public TasksResponseDto getTasksById(UUID id) {
        Tasks task = tasksRepository.findById(id)
                .orElseThrow(() -> new TasksNotFoundException(id));
        return mapToResponseDto(task);
    }

    // DELETE
    public void deleteTaskById(UUID id) {
        if (!tasksRepository.existsById(id)) {
            throw new TasksNotFoundException(id);
        }
        tasksRepository.deleteById(id);
    }

    // UPDATE
    public TasksResponseDto updateTask(UUID id, UpdateTasksDto dto) {
        Tasks task = tasksRepository.findById(id)
                .orElseThrow(() -> new TasksNotFoundException(id));

        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setStatus(dto.status());
        task.setCreated_at(dto.created_at());
        task.setDeadline(dto.deadline());

        task = tasksRepository.save(task);
        return mapToResponseDto(task);
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