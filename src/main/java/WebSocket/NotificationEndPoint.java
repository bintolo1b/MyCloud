package WebSocket;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import model.bo.NotificationBO;

@ServerEndpoint(value = "/notification", configurator = CustomConfigurator.class)
public class NotificationEndPoint {
	private static final Map<String, Session> clients = new ConcurrentHashMap<>();
	
	@OnOpen
	public void onOpen(Session session) {
		HttpSession httpSession = (HttpSession) session.getUserProperties().get("httpSession");
		String username = httpSession.getAttribute("username").toString();
		if (username == null) {
			try {
				session.close(new CloseReason(CloseReason.CloseCodes.VIOLATED_POLICY, "No username in session."));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			clients.put(username, session);
		}
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		JSONObject jsonOb = new JSONObject(message);
		String receiverUsername = jsonOb.getString("receiverUsername");
		String content = jsonOb.getString("content");
		String sentUsername = jsonOb.getString("sentUsername");
		String accessLink = jsonOb.getString("accessLink");
		int newId = NotificationBO.getInstance().insertNotification(receiverUsername, content, sentUsername, accessLink);
		
		Session receiverSession = clients.get(receiverUsername);
		if (receiverSession != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		    String time = LocalDateTime.now().format(formatter);
		    JSONObject notification = new JSONObject();
		    notification.put("id", newId);
		    notification.put("sentUsername", sentUsername);
		    notification.put("content", content);
		    notification.put("time", time);
		    notification.put("accessLink", accessLink);
            receiverSession.getAsyncRemote().sendText(notification.toString());
		}
	}
	
	@OnClose
    public void onClose(Session session) {
        String clientName = getClientNameFromSession(session);
        if (clientName != null) {
            clients.remove(clientName);
        }
    }
	
	private String getClientNameFromSession(Session session) {
        for (Map.Entry<String, Session> entry : clients.entrySet()) {
            if (entry.getValue().equals(session)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
