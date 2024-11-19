package api.UserAPI;

import java.io.IOException;
import java.io.PrintWriter;


import constant.Server;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.UserBO;

@WebServlet(urlPatterns = "/checkifuploadpossible")
public class CheckIfUploadPossible extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter pw = resp.getWriter();
		
		if (req.getParameter("size")!=null && req.getParameter("fileQuantity")!=null) {
			try {
				long size = Long.parseLong(req.getParameter("size"));
				int fileQuantity = Integer.parseInt(req.getParameter("fileQuantity"));
				String username = req.getSession(false).getAttribute("username").toString();
				
				if (UserBO.getInstance().checkIfEnoughCapacityToUpload(username, size) == false) {
	                pw.write("{\"message\": \"Not enough capacity to upload!\"}");
	            }
	            else if (fileQuantity > Server.MAX_FILE_QUANTITY_UPLOAD) {
	                pw.write("{\"message\": \"File quantity is too large, maximum is "+Server.MAX_FILE_QUANTITY_UPLOAD+"\"}");
	            }
	            else {
	            	pw.write("{\"message\": \"Upload possible!\"}");
	            }
			}
			catch (Exception e) {
				pw.write("{\"message\": \"Exception occur!\"}");
				e.printStackTrace();
			}
		}
		else {
			pw.write("{\"message\": \"Invalid parameters!\"}");
		}
	}
}
