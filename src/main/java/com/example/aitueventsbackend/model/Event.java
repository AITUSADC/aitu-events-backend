package com.example.aitueventsbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;


@Entity
@Table(name = "events")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, name = "event_date")
    private Instant eventDate;

    @Column(nullable = false)
    private String location;

    @CreationTimestamp
    @Column(nullable = false, updatable = false,name = "created_at")
    private Instant createdAt;

    public static Event createEvent(String title, String description, Instant eventDate, String location) {
        Event event = new Event();
        event.title = title;
        event.description = description;
        event.eventDate = eventDate;
        event.location = location;
        return event;
    }
}
