package api.UserAPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import constant.Server;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/getAvatar")
public class GetAvatar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		File avatar = new File(Server.USER_AVATAR_PATH + "\\" + username + ".jpg");
		if (avatar.exists()) {
			resp.setContentType(getServletContext().getMimeType(avatar.getName()));
            try (FileInputStream fis = new FileInputStream(avatar); 
                 OutputStream os = resp.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fis.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }
        } else {
        	resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
        }
	}

}
