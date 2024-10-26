package controller.FolderController;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.zip.ZipOutputStream;

import helper.CompressFolder;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.FolderBO;



@WebServlet(urlPatterns = "/downloadfoldercontroller")
public class DownloadFolderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("folderPath") !=null ) {
			String folderPath = req.getParameter("folderPath");
			
			if (FolderBO.getInstance().getFolderByPath(folderPath) != null) {
				File downloadedFolder = new File(folderPath);
				String encodedFolderName = URLEncoder.encode(downloadedFolder.getName(), "UTF-8").replace("+", "%20")+ ".zip";
				
				resp.setContentType("APPLICATION/OCTET-STREAM");
				
				resp.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFolderName);
				
				ServletOutputStream out = resp.getOutputStream();
				ZipOutputStream zos = new ZipOutputStream(out);
				
				CompressFolder.CompressFolderToZipFile(downloadedFolder, "", zos);
				
				zos.close();
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

