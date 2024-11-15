package api.UserAPI;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.User;
import model.bo.UserBO;

@WebServlet(urlPatterns = "/admin/checkifuseronline")
public class CheckIfUserOnline extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter pw = resp.getWriter();
		
		try {
			String username = req.getParameter("username");
			User user = UserBO.getInstance().getUser(username);
			String message;
			
            if (user == null)
            	message= "Username not found!";
            else
            	message = UserBO.getInstance().checkIfUserOnline(username) ? "Online" : "Offline";
            
			pw.write("{\"message\": \"" + message + "\"}");
			
		}
		catch (Exception e) {
			e.printStackTrace();
			pw.write("{\"message\": \"Exception occur!\"}");
		}
	}
}
