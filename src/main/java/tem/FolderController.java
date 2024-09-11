package tem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(urlPatterns = "/FolderController")
public class FolderController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getParameter("path");
        String action = req.getParameter("action");
        String folder = req.getParameter("folder");
       
        if ("delete".equalsIgnoreCase(action)) {
            deleteFolder(new File(path + File.separator + folder));
        } else if ("create".equalsIgnoreCase(action)) {
            createFolder(new File(path + File.separator + folder));
        }
       
        resp.sendRedirect("FileController?path=" + URLEncoder.encode(path, "UTF-8"));
    }

    private void deleteFolder(File folder) throws IOException {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolder(file);
                    } else {
                        file.delete();
                    }
                }
            }
        }
        folder.delete();
    }

    private void createFolder(File folder) {
        if (!folder.exists()) {
            folder.mkdirs(); // Create the folder (and any necessary but nonexistent parent directories)
        }
    }
}
