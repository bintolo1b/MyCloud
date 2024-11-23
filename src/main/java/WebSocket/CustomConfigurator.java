package WebSocket;

import org.apache.tomcat.websocket.server.DefaultServerEndpointConfigurator;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;

public class CustomConfigurator extends DefaultServerEndpointConfigurator {
	
	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		HttpSession httpSession = (HttpSession) request.getHttpSession();
		if (httpSession != null && httpSession.getAttribute("username") != null) {
            sec.getUserProperties().put("httpSession", httpSession);
		}
	}
}
