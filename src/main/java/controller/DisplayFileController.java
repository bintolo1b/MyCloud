package controller;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.ConvertFileToPDF;

@WebServlet(urlPatterns = "/displayfilecontroller")
public class DisplayFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("folderPath") != null && req.getParameter("fileName") != null) {
			String currentFolderPath = req.getParameter("folderPath");
			String fileName = req.getParameter("fileName");
			String filePath = currentFolderPath + File.separator + fileName;
			
	        byte[] pdfData = null;
	        if (filePath.endsWith(".txt"))
	        	pdfData = ConvertFileToPDF.convertTxtToPDF(filePath);
	        else if (filePath.endsWith(".docx"))
	        	pdfData = ConvertFileToPDF.convertDocxToPDF(filePath);
	        else if (filePath.endsWith(".pptx"))
	        	pdfData = ConvertFileToPDF.convertPPTXToPDF(filePath);
	        else if (filePath.endsWith(".xls"))
	        	pdfData = ConvertFileToPDF.convertXlsToPDF(filePath);
	        			
	        if (pdfData != null) {
	            resp.setContentType("application/pdf");
	            resp.setContentLength(pdfData.length);
	            resp.getOutputStream().write(pdfData);
	        }
		}
		else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
	}
	
	
}
