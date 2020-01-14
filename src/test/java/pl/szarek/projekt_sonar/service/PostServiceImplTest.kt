package pl.szarek.projekt_sonar.service

import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import pl.szarek.projekt_sonar.model.Post
import pl.szarek.projekt_sonar.repository.PostRepository
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class PostServiceImplTest {

    @Mock
    private lateinit var postRepository: PostRepository

    @InjectMocks
    private lateinit var postService: PostServiceImpl

    @Test
    fun should_update_post() {
        val postId = 1L
        val updatedPost = prepareUpdatedPost()

        given(postRepository.findById(postId))
            .willReturn(Optional.of(updatedPost))

        postService.updatePost(updatedPost)
        val post = postService.getPostById(postId).get()

        Assertions.assertEquals(updatedPost.content, post.content)
        Assertions.assertEquals(updatedPost.author, post.author)
    }

    private fun prepareUpdatedPost(): Post =
        Post("New post content", "Piotr")
}