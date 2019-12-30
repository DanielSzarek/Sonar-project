package pl.szarek.projekt_sonar.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.szarek.projekt_sonar.model.Post;
import pl.szarek.projekt_sonar.repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }
}
