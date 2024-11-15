package webInitation;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionManager implements HttpSessionListener, HttpSessionAttributeListener {
    private static Map<String, HttpSession> sessions = Collections.synchronizedMap(new HashMap<>());
    
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        if ("username".equals(event.getName())) {
            String username = (String) event.getValue();
            HttpSession session = event.getSession();
            sessions.put(username, session);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        String username = (String) session.getAttribute("username");
        if (username != null) {
            sessions.remove(username);
        }
    }

    public static boolean isUserOnline(String username) {
        return sessions.containsKey(username);
    }

    public static HttpSession getSessionByUsername(String username) {
        return sessions.get(username);
    }

    public static Map<String, HttpSession> getAllSessions() {
        return sessions;
    }
}
