package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.FileBO;

@WebServlet(urlPatterns = "/downloadfilecontroller")
public class DownloadFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("folderPath") != null && req.getParameter("downloadedFileName") != null) {
			String folderPath = req.getParameter("folderPath");
			String downloadedFileName = req.getParameter("downloadedFileName");
			String downloadedFileNamePath = folderPath + File.separator + downloadedFileName;
			
			if (FileBO.getInstance().getFileByPath(downloadedFileNamePath) != null) {
				String encodedFileName = URLEncoder.encode(downloadedFileName, "UTF-8").replace("+", "%20");
				
				resp.setContentType("APPLICATION/OCTET-STREAM");
				
				resp.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFileName);

				FileInputStream fis = new FileInputStream(new File(downloadedFileNamePath));
				
				ServletOutputStream out = resp.getOutputStream();
				
				byte[] buffer = new byte[1024];
				int bytesRead;

				while ((bytesRead = fis.read(buffer)) != -1) {
					out.write(buffer, 0, bytesRead);
				}

				fis.close();
				out.close();
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
