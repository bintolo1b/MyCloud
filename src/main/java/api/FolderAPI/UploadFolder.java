package api.FolderAPI;

import java.io.IOException;
import java.io.PrintWriter;
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
import model.bo.UserBO;


@WebServlet(urlPatterns = "/uploadfolder")
@MultipartConfig
public class UploadFolder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter pw = resp.getWriter();
		
		String folderPath = req.getParameter("folderPath");
		if (folderPath != null) {
			Collection<Part> parts = req.getParts();
			String username = req.getSession(false).getAttribute("username").toString();
			
			if (UserBO.getInstance().checkIfEnoughSpaceToUpload(username, parts) == false){
				pw.write("{\"message\": \"Not enough space to upload!\"}");
                return;
			}
			
			String uploadedFolderName = FolderBO.getInstance().saveUploadedFolderOnServer(folderPath, parts);
			if (uploadedFolderName == null) {
				pw.write("{\"message\": \"Exception occur!\"}");
        		return;
			}
			else {
        		FolderBO.getInstance().saveUploadedFolderOnDatabase(folderPath, uploadedFolderName);
        		Folder uploadedFolder = FolderBO.getInstance().getFolderByPath(folderPath + "\\" + uploadedFolderName);
        		FolderBO.getInstance().updateSizeOfFoldersInPathAfterUpload(folderPath, uploadedFolder.getSize());
        		pw.write("{\"message\": \"Uploaded successfully!\"}");
        		return;
			}
			
		}
		else {
			pw.write("{\"message\": \"Folder not found!\"}");
		}
		
	}
}
