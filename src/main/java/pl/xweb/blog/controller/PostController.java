package pl.xweb.blog.controller;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.xweb.blog.Exception.PageNotFoundException;
import pl.xweb.blog.model.Post;
import pl.xweb.blog.service.NotificationService;
import pl.xweb.blog.service.PostService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/posts")
@Log
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private NotificationService notificationService;


    @GetMapping("/view/{postId}")
    public String viewPostId(@PathVariable Long postId, Model theModel) {

        Post post = postService.getPostById(postId);
        log.info("--->>> getPostById(postId): " + post);

        if (post == null) {
            notificationService.addErrorMessage("Can not find post #" + postId);
            return "redirect:/";
            //throw new PageNotFoundException("Post " + postId + " not found.");
        }

        theModel.addAttribute("post", post);

        return "posts/view";
    }

    @ExceptionHandler(PageNotFoundException.class)
    public ModelAndView handleError(HttpServletRequest request, Exception exc) {
        log.warning("--->>> Request: " + request.getRequestURL() + " raised " + exc);

        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exc);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName("error/404");

        return mav;
    }

}
