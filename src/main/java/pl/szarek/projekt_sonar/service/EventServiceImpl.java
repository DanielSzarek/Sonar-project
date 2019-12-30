package pl.szarek.projekt_sonar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.szarek.projekt_sonar.model.Event;
import pl.szarek.projekt_sonar.repository.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class EventServiceImpl implements EventService{

    private EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id);
    }

    @Override
    public void saveEvent(Event event) {
        eventRepository.save(event);
    }
}
