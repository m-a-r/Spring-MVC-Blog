package pl.xweb.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class LoginServiceImpl implements LoginService {
//    TODO: comming back to last page (eventually)
//    @Autowired
//    private HttpSession session;
//
//    private String previousPageUrl = "/";
//
//
//    @Override
//    public void updatePreviousPageUrl(HttpServletRequest request) {
//        System.out.println("-------->>> In updatePreviousPageUrl()");
//        DefaultSavedRequest savedRequest = (DefaultSavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
//        if (savedRequest != null) {
//            previousPageUrl = savedRequest.getRequestURL();
//            System.out.println("-------->>> Updating previousPageUrl to: " + previousPageUrl);
//        } else {
//            previousPageUrl = request.getHeader("Referer");
//            System.out.println("-------->>> Updating previousPageUrl to REFERER: " + previousPageUrl);
//        }
//    }
//
//    @Override
//    public String getPreviousPageUrl() {
//        return previousPageUrl;
//    }
}
