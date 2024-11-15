package controller.AdminItemController;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.UserBO;
import model.dto.UserWithCapacityInfoDTO;

@WebServlet(urlPatterns = "/admin/home")
public class Admin_HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<UserWithCapacityInfoDTO> users = UserBO.getInstance().getAllUsersWithCapacityInfo();
		req.setAttribute("users", users);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/adminItem/admin_home.jsp");
		requestDispatcher.forward(req, resp);
	}
}
