package pl.xweb.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Component;
import pl.xweb.blog.service.LoginService;
import pl.xweb.blog.service.NotificationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;


@Component
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private LoginService loginService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

//        TODO: comming back to last page (eventually)
//        HttpSession httpSession = httpServletRequest.getSession();
//        DefaultSavedRequest savedRequest = (DefaultSavedRequest) httpSession.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
//        if (savedRequest == null) System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//        String requestURL = savedRequest.getRequestURL();
//        System.out.println("--->>> Request URL: " + requestURL);
//        String redirectURL = loginService.getPreviousPageUrl();

        notificationService.addInfoMessage("Login successful");
        String redirectURL = "/";

        httpServletResponse.sendRedirect(redirectURL);
    }

//    protected Optional<String> getPreviousPageRequestURL(HttpSession session) {
//        return Optional.ofNullable(session.getAttribute("SPRING_SECURITY_SAVED_REQUEST"))
//    }
}
