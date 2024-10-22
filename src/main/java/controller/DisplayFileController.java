package controller;

import java.io.File;
import java.io.IOException;
import helper.ConvertFileToPdfByteArray;
import helper.ConvertImgFileToImgByteArray;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/displayfilecontroller")
public class DisplayFileController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("folderPath") != null && req.getParameter("fileName") != null) {
			String currentFolderPath = req.getParameter("folderPath");
			String fileName = req.getParameter("fileName");
			String filePath = currentFolderPath + File.separator + fileName;
			
			File file = new File(filePath);
			if (file.exists()) {
				if (filePath.endsWith(".jpg") || filePath.endsWith(".jpeg") || filePath.endsWith(".png")) {
					byte[] imgData = ConvertImgFileToImgByteArray.imageToByteArr(filePath);
					if (filePath.endsWith(".jpg") || filePath.endsWith(".jpeg")) 
						 resp.setContentType("image/jpeg");
					else if (filePath.endsWith(".png"))
						resp.setContentType("image/png");
					else{
						resp.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
						return;
					}
					resp.setContentLength(imgData.length);
					resp.getOutputStream().write(imgData);
			        resp.getOutputStream().flush();
				}
				
				else {
					byte[] pdfData = ConvertFileToPdfByteArray.convertFileToPDF(filePath);
	    			
			        if (pdfData != null) {
			            resp.setContentType("application/pdf");
			            resp.setContentLength(pdfData.length);
			            resp.getOutputStream().write(pdfData);
			            resp.getOutputStream().flush();
			        }
			        else {
			            req.setAttribute("errorMessage", "Website does not support reading this file.");
			            req.getRequestDispatcher("/WEB-INF/views/fileReadingError.jsp").forward(req, resp);
			        }
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
