package pl.xweb.blog.service;

import pl.xweb.blog.model.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();
    List<Post> getLatestXPosts(int x);
    Post getPostById(Long postId);
    Post create(Post post);
    Post edit(Post post);
    void deleteById(Long postId);

}
