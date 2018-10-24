package pl.xweb.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import pl.xweb.blog.service.LoginService;
import pl.xweb.blog.service.NotificationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/login")
    public String showLoginPage(HttpServletRequest request) {

        // TODO: comming back to last page (eventually)
//        loginService.updatePreviousPageUrl(request);

        if (request.getParameter("logout") != null) {
            notificationService.addInfoMessage("User logged out succesfully");
        }

        if (request.getParameter("error") != null) {
            notificationService.addErrorMessage("Invalid login data");
        }

        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }
}