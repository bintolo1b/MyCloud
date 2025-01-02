package api.NotificationAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.NotificationBO;

@WebServlet(urlPatterns = "/checkreadnotification")
public class CheckReadNotification extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		StringBuilder sb = new StringBuilder();
		String line = "";
		BufferedReader reader = req.getReader();
		PrintWriter pw = resp.getWriter();
		
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		
		try{
			JSONObject jsonRequest = new JSONObject(sb.toString());
			int notificationId = jsonRequest.getInt("notificationId");
			String username = req.getSession().getAttribute("username").toString();
			
			String message = NotificationBO.getInstance().checkReadNotification(notificationId, username);
			pw.write("{\"message\": \"" + message + "\"}");
		}
		catch (Exception e) {
			pw.write("{\"message\": \"Exception occur!\"}");
		}
	}
	
}
