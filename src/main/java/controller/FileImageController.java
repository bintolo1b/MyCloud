package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import Helper.ConvertFileToPdfByteArray;
import Helper.ConvertImgFileToImgByteArray;
import Helper.TemporaryFolderHelper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/gettemporarydemoimgurl")
public class FileImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("folderPath") != null && req.getParameter("fileName") != null) {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			PrintWriter pw = resp.getWriter();
			
			
			try {
				String folderPath = req.getParameter("folderPath");
				String fileName = req.getParameter("fileName");
				String filePath = folderPath + File.separator + fileName;
				
				File file = new File(filePath);
				
				if (file.exists()) {
					String demoImgURL = "";
					String username = req.getSession().getAttribute("username").toString();
					ServletContext context = req.getServletContext();

					String tempImgFolder = context.getRealPath("/temporary/img");
					
					if (filePath.endsWith(".jpg") || filePath.endsWith(".jpeg") || filePath.endsWith(".png")) {
						byte[] imgData = ConvertImgFileToImgByteArray.imageToByteArr(filePath);
						demoImgURL = TemporaryFolderHelper.SaveImage(imgData, username, tempImgFolder);
						System.out.println(demoImgURL);
						pw.write("{\"demoImgURL\": \""+demoImgURL+"\"}");
						
					}
					else {
						byte[] pdfData = ConvertFileToPdfByteArray.convertFileToPDF(filePath);
						if (pdfData != null) {
							demoImgURL = TemporaryFolderHelper.convertFirstPDFPageToImgAndSave(pdfData, username, tempImgFolder);
							pw.write("{\"demoImgURL\": \""+demoImgURL+"\"}");
						}
						else {
							pw.write("{\"Message\": \"Website does not support reading this file.\"}");
						}
					}	
				}
				else {
					pw.write("{\"Message\": \"File doesn't exist.\"}");
				}
			}
			catch (Exception e) {
				e.printStackTrace();
				pw.write("{\"Message\": \"Exception occur!\"}");
			}
			
			pw.flush();
			pw.close();
			
		}
		else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
	}
}