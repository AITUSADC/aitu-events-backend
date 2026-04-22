package com.example.aitueventsbackend.repository;

import com.example.aitueventsbackend.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
    // upcoming
    Page<Event> findAllByEventDateGreaterThanEqual(Instant date, Pageable pageable);

    // past
    Page<Event> findAllByEventDateLessThan(Instant date, Pageable pageable);
}
