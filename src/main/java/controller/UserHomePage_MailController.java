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

@WebServlet(urlPatterns = "/userhomepage/mail")
public class UserHomePage_MailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String  SERVER_PATH = "D:\\MyPBL4Server";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		String username = session.getAttribute("username").toString();
		String folderPath = "";
		
		if (req.getParameter("folderPath")!=null)
			folderPath = req.getParameter("folderPath");
		else 
			folderPath = SERVER_PATH+"\\"+username;
		
		ArrayList<Folder> subFolders = new ArrayList<Folder>();
		ArrayList<File> files = new ArrayList<File>();
		
		Folder currentFolder = FolderBO.getInstance().getFolderByPath(folderPath);
		subFolders = FolderBO.getInstance().getAllSubfolderOfFolder(currentFolder);
		files = FileBO.getInstance().getAllFilesOfFolder(currentFolder);
		
		req.setAttribute("folderPath", folderPath);
		req.setAttribute("subFolders", subFolders);
		req.setAttribute("files", files);
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/userHomePageItems/userHomePage_mail.jsp");
		requestDispatcher.forward(req, resp);
	}
}
