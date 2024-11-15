package api.FileAPI;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.bo.FileBO;
import model.bo.FolderBO;
import model.bo.UserBO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

@WebServlet(urlPatterns = "/uploadfile")
@MultipartConfig
public class UploadFile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
    	resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter pw = resp.getWriter();
		
        String folderPath = req.getParameter("folderPath");
        if (folderPath!=null) {
        	Collection<Part> parts = req.getParts();
        	
			String username = req.getSession(false).getAttribute("username").toString();
			
			if (UserBO.getInstance().checkIfEnoughCapacityToUpload(username, parts) == false){
				pw.write("{\"message\": \"Not enough capacity to upload!\"}");
                return;
			}
        	
        	ArrayList<String> fileNames = FileBO.getInstance().saveUploadedFilesOnServer(folderPath, parts);
        	
    		FileBO.getInstance().saveUploadedFilesOnDatabase(folderPath, fileNames);
    		
    		long size = 0;
			for (Part part : parts) {
				if (part.getSubmittedFileName() != null)
					size += part.getSize();
			}
    		
    		FolderBO.getInstance().updateSizeOfFoldersInPathAfterUpload(folderPath, size);
    		   		
    		pw.write("{\"message\": \"Uploaded successfully!\"}");
        	
        }
        else {
        	pw.write("{\"message\": \"Folder not found!\"}");
        }
    }
}
