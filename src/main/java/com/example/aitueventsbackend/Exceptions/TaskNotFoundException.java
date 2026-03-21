package com.example.aitueventsbackend.Exceptions;

import java.util.UUID;

// This is your custom exception
public class TaskNotFoundException extends RuntimeException {

    // Constructor that takes the task id
    public TaskNotFoundException(UUID id) {
        super("Task not found with id: " + id);
    }
}