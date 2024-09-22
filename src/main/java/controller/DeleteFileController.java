package controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.FileBO;

@WebServlet(urlPatterns = "/deletefilecontroller")
public class DeleteFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("folderPath") != null && req.getParameter("deletedFileName") != null) {
			String currentFolderPath = req.getParameter("folderPath");
			String deletedFileName = req.getParameter("deletedFileName");
			String deletedFilePath = currentFolderPath + File.separator + deletedFileName;
			
			model.bean.File deletedFile = FileBO.getInstance().getFileByPath(deletedFilePath);
			if (deletedFile != null) {
				try {
					FileBO.getInstance().deleteFileOnServer(deletedFilePath);
					FileBO.getInstance().deleteFileOnDatabase(deletedFile);
					
					String encodedFolderPath = URLEncoder.encode(currentFolderPath, StandardCharsets.UTF_8.toString());
	        		resp.sendRedirect(req.getContextPath()+"/userhomepage?folderPath=" + encodedFolderPath);
				} catch (IOException e) {
					resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete folder");
					e.printStackTrace();
					return;
				}	
			}
			else {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
		}
		else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
	}
}
