package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.File;
import model.bean.Folder;
import model.bo.FileBO;
import model.bo.FolderBO;


@WebServlet(urlPatterns = "/userhomepage")
public class UserHomePageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String  SERVER_PATH = "D:\\MyPBL4Server";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null && session.getAttribute("username") != null) {
			String username = session.getAttribute("username").toString();
			String folderPath = "";
			
			if (req.getParameter("folderPath")!=null)
				folderPath = req.getParameter("folderPath");
			else 
				folderPath = SERVER_PATH+"\\"+username;
			
			ArrayList<Folder> subFolders = new ArrayList<Folder>();
			ArrayList<File> files = new ArrayList<File>();
			
			Folder currentFolder = FolderBO.getInstance().getFolderByPath(folderPath);
			if (FolderBO.getInstance().doesFolderBelongToUser(currentFolder, username)) {
				subFolders = FolderBO.getInstance().getAllSubfolderOfFolder(currentFolder);
				files = FileBO.getInstance().getAllFilesOfFolder(currentFolder);
			}
			else {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
			
			req.setAttribute("subFolders", subFolders);
			req.setAttribute("files", files);
			
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/userHomePage.jsp");
			requestDispatcher.forward(req, resp);
		}
	}
}
