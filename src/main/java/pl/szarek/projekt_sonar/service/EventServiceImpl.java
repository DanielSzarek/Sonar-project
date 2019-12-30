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

    @Override
    public boolean updateEvent(Event newEvent) {
        Optional<Event> eventToUpdate = getEventById(newEvent.getId());
        if(eventToUpdate.isPresent()) {
            Event event = eventToUpdate.get();
            event.setAuthor(newEvent.getAuthor());
            event.setDatOfAddition(newEvent.getDatOfAddition());
            event.setDescription(newEvent.getDescription());
            event.setTitle(newEvent.getTitle());
            eventRepository.saveAndFlush(event);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
