package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.bo.FileBO;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@WebServlet(urlPatterns = "/UploadFileController")
@MultipartConfig
public class UploadFileController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String folderPath = req.getParameter("folderPath");
        if (folderPath!=null) {
        	Collection<Part> parts = req.getParts();
        	if (!FileBO.getInstance().saveFilesOnServer(folderPath, parts)) {
        		resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        		return;
        	}
        	else {
        		FileBO.getInstance().saveFilesOnDatabase(folderPath, parts);
        		String encodedFolderPath = URLEncoder.encode(folderPath, StandardCharsets.UTF_8.toString());
        		resp.sendRedirect(req.getContextPath()+"/userhomepage?folderPath=" + encodedFolderPath);
        		return;
        	}
        }
        else {
        	resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
        }
    }
}
