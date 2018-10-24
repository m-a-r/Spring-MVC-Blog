package pl.xweb.blog.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.xweb.blog.model.Post;
import pl.xweb.blog.service.PostService;

import java.util.List;

@Controller
@Log
public class HomeController {

    @Autowired
    PostService postService;

    @RequestMapping("/")
    public String index(Model theModel) {

        List<Post> latest5Posts = postService.getLatestXPosts(5);
        log.info("--->>> latest5Posts: " + latest5Posts);
        theModel.addAttribute("latest5Posts", latest5Posts);

        List<Post> latest3Posts = postService.getLatestXPosts(3);
        log.info("--->>> latest3Posts: " + latest3Posts);
        theModel.addAttribute("latest3Posts", latest3Posts);

        return "index";
    }

    //TODO:
    @GetMapping("/posts/create")
    public String createPost() {
        return "redirect:/";
    }

// TODO:
// /posts/view/{id}

}
