package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bo.UserBO;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
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
		
		JSONObject jsonRequest = new JSONObject(sb.toString());
		String username = jsonRequest.getString("username");
		String password = jsonRequest.getString("password");
		PrintWriter pw = resp.getWriter();
		
		if (UserBO.getInstance().doesUserExits(username)) {
			if (!UserBO.getInstance().doesPasswordCorrect(username, password))
				pw.write("{\"message\": \"Password incorrect\"}");
			else {
		        pw.write("{\"message\": \"Login successfully\"}");
		        HttpSession session = req.getSession(false);
		        session.setAttribute("username", username);
		        session.setMaxInactiveInterval(30 * 60);
		    }
		}
		else {
			resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			pw.write("{\"message\": \"Account doesn't exist\"}");
		}
		
		pw.flush();
		pw.close();
			
	}
}
