package pl.xweb.blog.service;

import org.springframework.stereotype.Service;
import pl.xweb.blog.model.Post;
import pl.xweb.blog.model.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PostServiceStubImpl implements PostService {

    private List<Post> posts;

    @PostConstruct
    private void loadPosts() {

        posts = new ArrayList<>();
        posts.add(new Post(1L, "First Post", "<p>Line #1.</p><p>Line #2</p>", null));
        posts.add(new Post(2L, "Second Post",
                "Second post content:<ul><li>line 1</li><li>line 2</li></p>",
                new User(10L, "pesho10", "Peter Ivanov")));
        posts.add(new Post(3L, "Post #3", "<p>The post number 3 nice</p>",
                new User(10L, "merry", null)));
        posts.add(new Post(4L, "Forth Post", "<p>Not interesting post</p>", null));
        posts.add(new Post(5L, "Post Number 5", "<p>Just posting</p>", null));
        posts.add(new Post(6L, "Sixth Post", "<p>Another interesting post</p>", null));
    }

    @Override
    public List<Post> getAllPosts() {

        return posts;
    }

    @Override
    public List<Post> getLatestXPosts(int x) {

        List<Post> latestXPosts = new ArrayList<>();
        latestXPosts = posts.stream()
                .sorted((post1, post2) -> post2.getDateOfPublishing().compareTo(post1.getDateOfPublishing()))
                .limit(x)
                .collect(Collectors.toList());

        return latestXPosts;
    }

    @Override
    public Post getPostById(Long postId) {

        Post foundPost = posts.stream()
                .filter(post -> Objects.equals(post.getId(), postId))
                .findFirst()
                .orElse(null);

        if (foundPost == null) {
            throw new RuntimeException("Post not found: " + postId);
        }

        return foundPost;
    }

    @Override
    public Post create(Post newPost) {

        Long lastId = posts.stream()
                .mapToLong(post -> post.getId())
                .max()
                .getAsLong();

        newPost.setId(lastId+1);
        posts.add(newPost);

        return newPost;
    }

    @Override
    public Post edit(Post editedPost) {

        Long editedPostId = editedPost.getId();

        for (Post post : posts) {
            if (post.getId() == editedPostId) {
                post = editedPost;
                return post;
            }
        }

        throw new RuntimeException("Post not found: " + editedPostId);
    }

    @Override
    public void deleteById(Long postId) {

        try {
            Post postToDelete = getPostById(postId);
            posts.remove(postToDelete);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
