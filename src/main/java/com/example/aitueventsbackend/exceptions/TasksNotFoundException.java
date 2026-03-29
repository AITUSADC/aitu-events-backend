package com.example.aitueventsbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TasksNotFoundException extends RuntimeException {

    public TasksNotFoundException(UUID id) {
        super("Task not found with id: " + id);
    }
}