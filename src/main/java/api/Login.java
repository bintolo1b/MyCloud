package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bo.UserBO;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
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
			
			String message = UserBO.getInstance().CheckLogin(username, password);
			pw.write("{\"message\": \"" + message + "\"}");
			if (message.equals("Login successfully!")) {
				HttpSession session = req.getSession();
		        session.setAttribute("username", username);
		        session.setMaxInactiveInterval(30 * 60);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			pw.write("{\"message\": \"Exception occur!\"}");
		}
		
		pw.flush();
		pw.close();
			
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
		dispatcher.forward(req, resp);
	}
}
