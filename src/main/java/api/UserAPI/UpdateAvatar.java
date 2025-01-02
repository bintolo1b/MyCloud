package api.UserAPI;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.UserBO;

@WebServlet(urlPatterns = "/updateAvatar")
@MultipartConfig
public class UpdateAvatar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter pw = resp.getWriter();
		String username = req.getSession().getAttribute("username").toString();
		
		
		try {
			String message;
			message = UserBO.getInstance().updateAvatar(username, req.getPart("avatar"));
			pw.write("{\"message\": \"" + message + "\"}");
			
		}
		catch (Exception e) {
			e.printStackTrace();
			pw.write("{\"message\": \"Exception occur!\"}");
		}
	}
}
