package WebSocket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.json.JSONObject;

import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import model.bo.NotificationBO;

@ServerEndpoint("/notification")
public class NotificationEndPoint {
	private static final Map<String, Session> clients = new ConcurrentHashMap<>();
	
	@OnOpen
	public void onOpen(Session session) {
		String clientUsername = session.getRequestParameterMap().get("username").get(0);
		clients.put(clientUsername, session);
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		JSONObject jsonOb = new JSONObject(message);
		String receiverUsername = jsonOb.getString("receiverUsername");
		String content = jsonOb.getString("content");
		String sentUsername = jsonOb.getString("sentUsername");
		String accessLink = jsonOb.getString("accessLink");
		NotificationBO.getInstance().insertNotification(receiverUsername, content, sentUsername, accessLink);
		Session receiverSession = clients.get(receiverUsername);
		if (receiverSession != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		    String time = LocalDateTime.now().format(formatter);
		    JSONObject notification = new JSONObject();
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
