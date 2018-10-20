package pl.xweb.blog.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.xweb.blog.model.Post;
import pl.xweb.blog.service.PostService;

@Controller
@RequestMapping("/posts")
@Log
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/view/{postId}")
    public String viewPostId(@PathVariable Long postId, Model theModel) {

        Post post = postService.getPostById(postId);
        log.info("--->>> getPostById(postId): " + post);

        theModel.addAttribute("post", post);

        return "posts/view";
    }

}
