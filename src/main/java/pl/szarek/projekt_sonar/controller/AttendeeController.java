package pl.szarek.projekt_sonar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.szarek.projekt_sonar.model.Attendee;
import pl.szarek.projekt_sonar.model.Event;
import pl.szarek.projekt_sonar.service.AttendeeService;
import pl.szarek.projekt_sonar.service.EventService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attendees")
@CrossOrigin
public class AttendeeController {

    private AttendeeService attendeeService;
    private EventService eventService;

    @Autowired
    public AttendeeController(AttendeeService attendeeService, EventService eventService) {
        this.attendeeService = attendeeService;
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<Attendee>> getAttendessByEvent(@RequestParam Long id) {
        return new ResponseEntity<>(attendeeService.findAllByEvent(id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attendee> getAttendeeById(@PathVariable("id") Long id) {
        Optional<Attendee> attendee = attendeeService.findAttendeeById(id);
        return attendee.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/{id}")
    public ResponseEntity<HttpStatus> addAttendeToEvent(@RequestBody Attendee attendee, @PathVariable("id") Long id) {
        Optional<Event> event = eventService.getEventById(id);
        if (!event.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            attendee.setEvent(event.get());
            attendeeService.addAttendee(attendee);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAttendeFromEvent(@PathVariable("id") Long id) {
        attendeeService.deleteAttendee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
