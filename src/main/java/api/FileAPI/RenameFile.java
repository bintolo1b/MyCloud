package api.FileAPI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.FileBO;

@WebServlet(urlPatterns = "/renamefile")
public class RenameFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = req.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		
		PrintWriter pw = resp.getWriter();
		
		try {
			JSONObject jsonRequest = new JSONObject(sb.toString());
			String folderPath = jsonRequest.getString("folderPath");
			String oldFileName = jsonRequest.getString("oldName");
			String newFileName = jsonRequest.getString("newName");
			String username = req.getSession(false).getAttribute("username").toString();
			
			String message = FileBO.getInstance().changeFileName(folderPath, oldFileName, newFileName, username);
			pw.write("{\"message\": \"" + message + "\"}");
		}
		catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			e.printStackTrace();
		}
	}
}
