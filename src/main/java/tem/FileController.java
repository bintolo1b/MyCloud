package tem;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/FileController")
public class FileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PARENT_PATH = "C:\\Users\\ADMIN\\Downloads";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getParameter("path");
		if (path==null || path.isEmpty()) 
			path = PARENT_PATH;
		listFiles(path, req, resp);
	}

	private void listFiles(String path, HttpServletRequest req, HttpServletResponse resp) {
		File filePath = new File(path);
		File[] fileList = filePath.listFiles();
		
		ArrayList<String> folders = new ArrayList<String>();
		ArrayList<String> files = new ArrayList<String>();
		
		for (File file : fileList) {
			if (file.isDirectory())
				folders.add(file.getName());
			else
				files.add(file.getName());
		}
		
		req.setAttribute("path", path);
		req.setAttribute("folders", folders);
		req.setAttribute("files", files);
		
		try {
			req.getRequestDispatcher("/WEB-INF/views/listFiles.jsp").forward(req, resp);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
