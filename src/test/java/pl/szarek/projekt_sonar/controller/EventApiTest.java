package pl.szarek.projekt_sonar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import pl.szarek.projekt_sonar.model.Event;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventApiTest {

    @Autowired
    MockMvc mockMvc;

    private List<Event> eventList;

    @Before
    public void init() throws Exception {
        eventList = new ArrayList<>();
        eventList.add(new Event("Daniel",  "Meetup", "Lets meet up"));
        eventList.add(new Event("Kamil",  "Event", "New event"));
        eventList.add(new Event("Przemek",  "Wow!", "Omg!"));

        for(Event event: eventList) {
            String json = new ObjectMapper().writeValueAsString(event);
            mockMvc.perform(post("/api/events")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json));
        }
    }

    @After
    public void clear() throws Exception {
        mockMvc.perform(delete("/api/events/tests/clear"));
    }

    @Test
    public void should_get_events() throws Exception {
        mockMvc.perform(get("/api/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void should_get_event_by_id() throws Exception {
        Event event = eventList.get(0);
        System.out.println(event.getId());
        mockMvc.perform(get("api/events/{id}", event.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dateOfAddition", Is.is(event.getDateOfAddition())))
                .andExpect(jsonPath("$.title", Is.is(event.getTitle())))
                .andExpect(jsonPath("$.description", Is.is(event.getDescription())));
    }

    @Test
    public void should_add_event() throws Exception {
        Event event = new Event("Danielos", "Meetup2", "Lets meet up2");
        String json = new ObjectMapper().writeValueAsString(event);
        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_delete_event() throws Exception {
        mockMvc.perform(delete("/api/events/{id}", 3L))
                .andExpect(status().isOk());
    }

    @Test
    public void should_not_delete_event() throws Exception {
        mockMvc.perform(delete("/api/events/{id}", 0.0))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_delete_all_events_and_get_size_0() throws Exception {
        mockMvc.perform(delete("/api/events/tests/clear"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/events"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
