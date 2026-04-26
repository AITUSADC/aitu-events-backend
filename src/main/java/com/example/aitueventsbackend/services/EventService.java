package com.example.aitueventsbackend.services;

import com.example.aitueventsbackend.exceptions.EventNotFoundException;
import com.example.aitueventsbackend.model.Event;
import com.example.aitueventsbackend.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public Event create(String title, String description, Instant eventDate, String location) {
        Event event = Event.createEvent(title, description, eventDate, location);
        return eventRepository.save(event);
    }

    public Event fullUpdate(UUID id, String title, String description, Instant eventDate, String location) {
        Event event = eventRepository.findById(id).orElseThrow(EventNotFoundException::new);

        event.setTitle(title);
        event.setDescription(description);
        event.setEventDate(eventDate);
        event.setLocation(location);

        return eventRepository.save(event);
    }

    public Event partialUpdate(UUID id, String title, String description, Instant eventDate, String location) {
        Event event = eventRepository.findById(id).orElseThrow(EventNotFoundException::new);

        if (title != null)       event.setTitle(title);
        if (description != null) event.setDescription(description);
        if (eventDate != null)   event.setEventDate(eventDate);
        if (location != null)    event.setLocation(location);

        return eventRepository.save(event);
    }

    public void delete(UUID id) {
        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundException();
        }
        eventRepository.deleteById(id);
    }

    public Page<Event> getUpcoming(Pageable pageable) {
        Pageable sorted = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "eventDate"));
        return eventRepository.findAllByEventDateGreaterThanEqual(Instant.now(), sorted);
    }

    public Page<Event> getPast(Pageable pageable) {
        Pageable sorted = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                Sort.by(Sort.Direction.DESC, "eventDate"));
        return eventRepository.findAllByEventDateLessThan(Instant.now(), sorted);
    }

    }
