package controller.FolderController;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.Folder;
import model.bo.FolderBO;

@WebServlet(urlPatterns = "/deletefoldercontroller")
public class DeleteFolderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("folderPath") != null && req.getParameter("deletedFolderName") != null) {
			String currentFolderPath = req.getParameter("folderPath");
			String deletedSubFolder = req.getParameter("deletedFolderName");
			String deletedSubFolderPath = currentFolderPath + File.separator + deletedSubFolder;
			
			Folder deletedFolder = FolderBO.getInstance().getFolderByPath(deletedSubFolderPath);
			if (deletedFolder != null) {
				try {
					FolderBO.getInstance().deleteFolderOnServer(new File(deletedFolder.getPath()));
					FolderBO.getInstance().deleteFolderOnDatabase(deletedFolder);
					FolderBO.getInstance().updateSizeOfFoldersInPathAfterDelete(currentFolderPath, deletedFolder.getSize());
					
					String encodedFolderPath = URLEncoder.encode(currentFolderPath, StandardCharsets.UTF_8.toString());
	        		resp.sendRedirect(req.getContextPath()+"/userhomepage/main?folderPath=" + encodedFolderPath);
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
