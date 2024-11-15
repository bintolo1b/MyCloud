package controller.AdminItemController;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/admin/addcapacity")
public class Admin_AddCapacityController extends HttpServlet {
	 private static final long serialVersionUID = 1L;
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/adminItem/admin_addCapacity.jsp");
		 requestDispatcher.forward(req, resp);
	}
}
