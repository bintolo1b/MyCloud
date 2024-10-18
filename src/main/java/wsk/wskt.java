package wsk;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/chat")
public class wskt {
	
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("new connect: "+session.getId());
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
       System.out.println(message);
    }
	
	@OnClose
	public void OnClose() {
		System.out.println("close");
	}
}
