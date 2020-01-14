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
import pl.szarek.projekt_sonar.model.Post;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostApiTest {

    @Autowired
    MockMvc mockMvc;

    private List<Post> postList;

    @Before
    public void init() throws Exception {
        postList = new ArrayList<>();
        postList.add(new Post("Daniel", "Content"));
        postList.add(new Post("Daniel", "Attention!"));
        postList.add(new Post("Kamil", "Nice news"));

        for(Post post: postList) {
            String json = new ObjectMapper().writeValueAsString(post);
            mockMvc.perform(post("/api/posts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json));
        }
    }

    @After
    public void clear() throws Exception {
        mockMvc.perform(delete("/api/posts/tests/clear"));
    }

    @Test
    public void should_get_post() throws Exception {
        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void should_get_post_by_id() throws Exception {
        Post post = postList.get(0);
        System.out.println(post.getId());
        mockMvc.perform(get("api/posts/{id}", post.getId()+1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dateOfAddition", Is.is(post.getDateOfAddition())))
                .andExpect(jsonPath("$.author", Is.is(post.getAuthor())))
                .andExpect(jsonPath("$.content", Is.is(post.getContent())));
    }

    @Test
    public void should_add_post() throws Exception {
        Post post = new Post("Kamilos", "Test content");
        String json = new ObjectMapper().writeValueAsString(post);
        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_delete_post() throws Exception {
        mockMvc.perform(delete("/api/posts/{id}", 3L))
                .andExpect(status().isOk());
    }

    @Test
    public void should_not_delete_post() throws Exception {
        mockMvc.perform(delete("/api/posts/{id}", 0.0))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_delete_all_posts_and_get_size_0() throws Exception {
        mockMvc.perform(delete("/api/posts/tests/clear"))
                .andExpect(status().isOk());
        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
