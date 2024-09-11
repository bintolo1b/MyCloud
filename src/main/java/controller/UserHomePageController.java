package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/userhomepage")
public class UserHomePageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String  SERVER_PATH = "D:\\MyPBL4Server";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
			String username = session.getAttribute("username").toString();
			
			File originUserFolder = new File(SERVER_PATH + File.separator + username);
			File[] fileList = originUserFolder.listFiles();
			ArrayList<String> folders = new ArrayList<String>();
			ArrayList<String> files = new ArrayList<String>();
			for (File file:fileList) {
				if (file.isDirectory())
					folders.add(file.getName());
				else if (file.isFile())
					files.add(file.getName());
			}
			
			req.setAttribute("folders", folders);
			req.setAttribute("files", files);
			
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/userHomePage.jsp");
			requestDispatcher.forward(req, resp);
		}
	}
}
