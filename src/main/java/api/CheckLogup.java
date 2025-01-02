package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.User;
import model.bo.FolderBO;
import model.bo.UserBO;

@WebServlet(urlPatterns = "/checkLogup")
public class CheckLogup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		
		PrintWriter pw = resp.getWriter();
		
		try {
			JSONObject jsonRequest = new JSONObject(sb.toString());
			String username = jsonRequest.getString("username");
			String password = jsonRequest.getString("password");
			String verifyPassword = jsonRequest.getString("verifyPassword");
			String fullName = jsonRequest.getString("fullName");
			
			String message = UserBO.getInstance().CheckLogupNewUser(username, password, verifyPassword,fullName);
			pw.write("{\"message\": \""+message+"\"}");
			if (message.equals("Logup successfully!")) {
				UserBO.getInstance().addNewUser(username, password, fullName);
				User newUser = UserBO.getInstance().getUser(username);
				UserBO.getInstance().createDefaultAvatar(username);
				FolderBO.getInstance().createRootFolder(newUser);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			pw.write("{\"message\": \"Exception occur!\"}");
		}
		pw.flush();
		pw.close();
	}
}
