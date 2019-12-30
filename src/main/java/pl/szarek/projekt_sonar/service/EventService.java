package pl.szarek.projekt_sonar.service;

import pl.szarek.projekt_sonar.model.Event;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface EventService {

    List<Event> getAllEvents();

    Optional<Event> getEventById(Long id);

    void saveEvent(Event event);
}
