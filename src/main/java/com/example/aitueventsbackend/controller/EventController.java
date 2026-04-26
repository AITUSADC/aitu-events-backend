package com.example.aitueventsbackend.controller;

import com.example.aitueventsbackend.dto.EventCreateDto;
import com.example.aitueventsbackend.dto.EventFullUpdateDto;
import com.example.aitueventsbackend.dto.EventResponseDto;
import com.example.aitueventsbackend.dto.EventUpdateDto;
import com.example.aitueventsbackend.model.Event;
import com.example.aitueventsbackend.services.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Tag(name = "Events", description = "Operations for managing events")
public class EventController {

    private final EventService eventService;

    @PostMapping
    @Operation(summary = "Create a new event")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Event created"),
            @ApiResponse(responseCode = "404", description = "Validation error")
    })
    public EventResponseDto create(@Valid @RequestBody EventCreateDto createDto) {
        return toResponseDto(eventService.create(createDto.title(), createDto.description(),
                createDto.eventDate(), createDto.location()));
    }

    @GetMapping("/upcoming")
    @Operation(summary = "Upcoming events")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Upcoming events returned"),
            @ApiResponse(responseCode = "404", description = "Events not found")
    })
    public Page<EventResponseDto> getUpcoming(Pageable pageable) {
        return eventService.getUpcoming(pageable).map(this::toResponseDto);
    }

    @GetMapping("/past")
    @Operation(summary = "Past events")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Past events returned"),
            @ApiResponse(responseCode = "404", description = "Events not found")
    })
    public Page<EventResponseDto> getPast(Pageable pageable) {
        return eventService.getPast(pageable).map(this::toResponseDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a event by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Event updated"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public EventResponseDto fullUpdate(@Parameter(description = "ID of event", required = true) @PathVariable UUID id,
                                       @Valid @RequestBody EventFullUpdateDto fullUpdateDto) {
        return toResponseDto(eventService.fullUpdate(id, fullUpdateDto.title(), fullUpdateDto.description(),
                fullUpdateDto.eventDate(), fullUpdateDto.location()));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Partially update a event by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Event partially updated"),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public EventResponseDto partiallyUpdate(@Parameter(description = "ID of event", required = true) @PathVariable UUID id,
                                            @RequestBody EventUpdateDto updateDto) {
        return toResponseDto(eventService.partialUpdate(id, updateDto.title(), updateDto.description(),
                updateDto.eventDate(), updateDto.location()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Detele a event")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Event deleted"),
            @ApiResponse(responseCode = "404", description = "Event not found")
    })
    public String delete(@Parameter(description = "ID of event", required = true) @PathVariable UUID id) {
        eventService.delete(id);
        return "Event deleted successfully.";
    }


    private EventResponseDto toResponseDto(Event event) {
        return new EventResponseDto(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getEventDate(),
                event.getLocation(),
                event.getCreatedAt()
        );
}
}
