package controller.UserInforController;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.User;
import model.bo.UserBO;

@WebServlet(urlPatterns = "/userinformation")
public class UserInformationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getSession().getAttribute("username").toString();
		User user = UserBO.getInstance().getUser(username);
		req.setAttribute("user", user);
		req.getRequestDispatcher("/WEB-INF/views/personalInformation.jsp").forward(req, resp);
	}
}
