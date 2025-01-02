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
			JSONArray filesJSONarray = FileBO.getInstance().searchFilesToJsonArray(username, searchContent);
			JSONArray foldersJSONarray = FolderBO.getInstance().searchFoldersToJsonArray(username, searchContent);
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < filesJSONarray.length(); i++) {
				jsonArray.put(filesJSONarray.get(i));
			}
			for (int i = 0; i < foldersJSONarray.length(); i++) {
				jsonArray.put(foldersJSONarray.get(i));
			}
			pw.write(jsonArray.toString());
		}
	}
}
