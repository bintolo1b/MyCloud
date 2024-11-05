package controller.FolderController;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.bean.Folder;
import model.bo.FolderBO;


@WebServlet(urlPatterns = "/uploadfoldercontroller")
@MultipartConfig
public class UploadFolderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("folderPath") != null) {
			String currentFolderPath = req.getParameter("folderPath");
			Collection<Part> parts = req.getParts();
			String uploadedFolderName = FolderBO.getInstance().saveUploadedFolderOnServer(currentFolderPath, parts);
			if (uploadedFolderName == null) {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        		return;
			}
			else {
        		FolderBO.getInstance().saveUploadedFolderOnDatabase(currentFolderPath, uploadedFolderName);
        		Folder uploadedFolder = FolderBO.getInstance().getFolderByPath(currentFolderPath + "\\" + uploadedFolderName);
        		FolderBO.getInstance().updateSizeOfFoldersInPathAfterUpload(currentFolderPath, uploadedFolder.getSize());
        		String encodedFolderPath = URLEncoder.encode(currentFolderPath, StandardCharsets.UTF_8.toString());
        		resp.sendRedirect(req.getContextPath()+"/userhomepage/main?folderPath=" + encodedFolderPath);
        		return;
			}
			
		}
		else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
	}
}
