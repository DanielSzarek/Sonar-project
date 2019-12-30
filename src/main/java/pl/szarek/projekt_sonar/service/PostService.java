package pl.szarek.projekt_sonar.service;

import pl.szarek.projekt_sonar.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    List<Post> getAllPosts();

    Optional<Post> getPostById(Long id);

    void savePost(Post post);

    boolean updatePost(Post post);

    void deletePost(Long id);
}
