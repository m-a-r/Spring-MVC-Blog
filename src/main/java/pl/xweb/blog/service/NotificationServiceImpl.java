package pl.xweb.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    public static final String NOTIFY_MSG_SESSION_KEY = "siteNotificationMessages";

    @Autowired
    HttpSession httpSession;

    @Override
    public void addInfoMessage(String msg) {
        addNotificationMessage(NotificationMessageType.INFO, msg);
    }

    @Override
    public void addErrorMessage(String msg) {
        addNotificationMessage(NotificationMessageType.ERROR, msg);
    }

    private void addNotificationMessage(NotificationMessageType theNotificationMessageType, String msg) {

        List<NotificationMessage> notificationMessages =
                (List<NotificationMessage>) httpSession.getAttribute(NOTIFY_MSG_SESSION_KEY);
        if (notificationMessages == null) {
            notificationMessages = new ArrayList<>();
        }
        notificationMessages.add(new NotificationMessage(theNotificationMessageType, msg));
        httpSession.setAttribute(NOTIFY_MSG_SESSION_KEY, notificationMessages);
    }

    public enum NotificationMessageType {

        INFO,
        ERROR
    }

    public class NotificationMessage {

        NotificationMessageType type;
        String text;

        public NotificationMessage(NotificationMessageType type, String text) {

            this.type = type;
            this.text = text;
        }

        public NotificationMessageType getType() {
            return type;
        }

        public String getText() {
            return text;
        }
    }
}
