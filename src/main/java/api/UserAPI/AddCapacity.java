package api.UserAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.UserBO;

@WebServlet(urlPatterns = "/admin/addCapacity")
public class AddCapacity extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter pw = resp.getWriter();
		
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();
		
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		
		try {
			JSONObject jsonRequest = new JSONObject(sb.toString());
			String username = jsonRequest.getString("username");
			int addedCapacity = jsonRequest.getInt("addedCapacity");

			String message;
			message = UserBO.getInstance().addCapacity(username, addedCapacity);
			pw.write("{\"message\": \"" + message + "\"}");

		} catch (Exception e) {
			e.printStackTrace();
			pw.write("{\"message\": \"Exception occur!\"}");
		}
	}
}
