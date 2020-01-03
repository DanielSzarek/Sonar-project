package pl.szarek.projekt_sonar.service;

import pl.szarek.projekt_sonar.model.Attendee;
import pl.szarek.projekt_sonar.model.Event;

import javax.accessibility.AccessibleText;
import java.util.List;
import java.util.Optional;

public interface AttendeeService {

    List<Attendee> findAllByEvent(Long id);

    Optional<Attendee> findAttendeeById(Long id);

    void addAttendee(Attendee attendee);

    void deleteAttendee(Long id);

    boolean updateAttendee(Attendee attendee);
}
