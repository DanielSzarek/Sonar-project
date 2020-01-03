package pl.szarek.projekt_sonar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.szarek.projekt_sonar.model.Attendee;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {

    List<Attendee> findAllByEvent_Id(Long id);

    Optional<Attendee> findById(Long id);
}
