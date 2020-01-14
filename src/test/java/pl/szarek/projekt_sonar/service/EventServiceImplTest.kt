package pl.szarek.projekt_sonar.service

import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import pl.szarek.projekt_sonar.model.Event
import pl.szarek.projekt_sonar.repository.EventRepository
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class EventServiceImplTest {

    @Mock
    private lateinit var eventRepository: EventRepository

    @InjectMocks
    private lateinit var eventService: EventServiceImpl

    @Test
    fun should_update_event() {
        val eventId = 1L
        val updatedEvent = prepareUpdatedEvent()

        given(eventRepository.findById(eventId))
            .willReturn(Optional.of(updatedEvent))

        eventService.updateEvent(updatedEvent)
        val event = eventService.getEventById(eventId).get()

        Assertions.assertEquals(updatedEvent.author, event.author)
        Assertions.assertEquals(updatedEvent.title, event.title)
        Assertions.assertEquals(updatedEvent.description, event.description)
    }

    private fun prepareUpdatedEvent(): Event =
        Event("Piotr", "Event", "Event description")
}