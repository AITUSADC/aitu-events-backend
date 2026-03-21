package com.example.aitueventsbackend.Model;

import com.example.aitueventsbackend.TasksStatus.TaskStatus;
import java.time.LocalDate;
import java.util.UUID;

public class Tasks {
    private UUID id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate created_at;  // renamed
    private LocalDate deadline;    // renamed

    public Tasks(UUID id, String title, String description, TaskStatus status,
                 LocalDate created_at, LocalDate deadline) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.created_at = created_at;
        this.deadline = deadline;
    }

    // Getters and setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

    public LocalDate getCreated_at() { return created_at; }  // renamed
    public void setCreated_at(LocalDate created_at) { this.created_at = created_at; } // renamed

    public LocalDate getDeadline() { return deadline; }      // renamed
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }       // renamed
}