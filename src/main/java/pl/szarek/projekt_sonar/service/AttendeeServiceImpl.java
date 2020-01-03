package pl.szarek.projekt_sonar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.szarek.projekt_sonar.model.Attendee;
import pl.szarek.projekt_sonar.repository.AttendeeRepository;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class AttendeeServiceImpl implements AttendeeService {

    private AttendeeRepository attendeeRepository;

    @Autowired
    public AttendeeServiceImpl(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    @Override
    public List<Attendee> findAllByEvent(Long id) {
        return attendeeRepository.findAllByEvent_Id(id);
    }

    @Override
    public Optional<Attendee> findAttendeeById(Long id) {
        return attendeeRepository.findById(id);
    }

    @Override
    public void addAttendee(Attendee attendee) {
        attendeeRepository.save(attendee);
    }

    @Override
    public void deleteAttendee(Long id) {
        attendeeRepository.deleteById(id);
    }

    @Override
    public boolean updateAttendee(Attendee attendee) {
        return false;
    }
}
