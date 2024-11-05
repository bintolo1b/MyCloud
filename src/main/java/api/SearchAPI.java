package api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bean.File;
import model.bean.Folder;
import model.bo.FileBO;
import model.bo.FolderBO;

@WebServlet(urlPatterns = {"/search"})
public class SearchAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter pw = resp.getWriter();
		
		String username = req.getSession().getAttribute("username").toString();
		String searchContent = req.getParameter("searchContent");
		if (searchContent == null || searchContent.isEmpty()) {
			pw.write("{\"Message\": \"Search content is empty.\"}");
            return;
        }
		else {
			ArrayList<File> files = FileBO.getInstance().searchFiles(username, searchContent);
			ArrayList<Folder> folders = FolderBO.getInstance().searchFolders(username, searchContent);
			
			JSONArray jsonArray = new JSONArray();
			
			for (File file : files) {
				JSONObject jsonObject = new JSONObject();
				String fileName = file.getName();
				String fileType = "";
				
				int dotIndex = fileName.lastIndexOf(".");
				if (dotIndex == -1 || (dotIndex!=-1 && dotIndex == fileName.length() - 1)) {
					fileType = "file";
				} else if (dotIndex != -1 && dotIndex != fileName.length() - 1) {
					fileType = fileName.substring(dotIndex + 1);
				}
				
				String folderPath = file.getPath().substring(0, file.getPath().lastIndexOf("\\"));
				jsonObject.put("Name", file.getName());	
				jsonObject.put("Type", fileType);
				jsonObject.put("UploadDate", file.getFormattedUploadedDate());
				jsonObject.put("folderPath", folderPath);
				
				jsonArray.put(jsonObject);
			}
			
			for (Folder folder : folders) {
				JSONObject jsonObject = new JSONObject();
				
				jsonObject.put("Name", folder.getName());
				jsonObject.put("Type", "folder");
				jsonObject.put("UploadDate", folder.getFormattedUploadedDate());
				jsonObject.put("Path", folder.getPath());
				
				jsonArray.put(jsonObject);
			}
			pw.write(jsonArray.toString());
		}
	}
}
