package pl.szarek.projekt_sonar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import pl.szarek.projekt_sonar.model.Attendee;
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
public class AttendeeApiTest {

    @Autowired
    MockMvc mockMvc;

    private List<Attendee> attendeeList;

    @Before
    public void init() throws Exception {
        // Firstly we add one event that will be a fk for attendees
        Event event = new Event("Przemek", "New Event!", "IT party");
        String json = new ObjectMapper().writeValueAsString(event);
        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        attendeeList = new ArrayList<>();
        attendeeList.add(new Attendee("Daniel"));
        attendeeList.add(new Attendee("Przemek"));
        attendeeList.add(new Attendee("Kamil"));

        for(Attendee attendee: attendeeList) {
            String attendeeJson = new ObjectMapper().writeValueAsString(attendee);
            mockMvc.perform(post("/api/attendees/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(attendeeJson));
        }
    }

    @After
    public void clear() throws Exception {
        mockMvc.perform(delete("/api/attendees/tests/clear"));
    }

    @Test
    public void should_get_attendess_for_event() throws Exception {
        mockMvc.perform(get("/api/attendees")
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void should_add_attendee() throws Exception {
        Attendee attendee = new Attendee("Janusz");
        String json = new ObjectMapper().writeValueAsString(attendee);
        mockMvc.perform(post("/api/attendees/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_delete_attendee() throws Exception {
        mockMvc.perform(delete("/api/attendees/{id}", 3L))
                .andExpect(status().isOk());
    }

    @Test
    public void should_not_delete_attendee() throws Exception {
        mockMvc.perform(delete("/api/attendees/{id}", 0.0))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_delete_all_attendees_and_get_size_0() throws Exception {
        mockMvc.perform(delete("/api/attendees/tests/clear"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/attendees")
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
