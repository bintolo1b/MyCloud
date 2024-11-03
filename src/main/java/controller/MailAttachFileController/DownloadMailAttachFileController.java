package controller.MailAttachFileController;

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
import jakarta.servlet.http.HttpSession;
import model.bean.MailAttachFile;
import model.bo.MailAttachFileBO;

@WebServlet(urlPatterns = "/downloadmailattachfilecontroller")
public class DownloadMailAttachFileController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("mailAttachFilePath") != null) {
			String downloadedMailAttachFilePath = req.getParameter("mailAttachFilePath");
			
			MailAttachFile mailAttachFile = MailAttachFileBO.getInstance().getMailAttachFileByPath(downloadedMailAttachFilePath);
			HttpSession session = req.getSession(false);
			String username = session.getAttribute("username").toString();
			if (mailAttachFile != null){
				if (MailAttachFileBO.getInstance().checkIfUserHavePermissionToDownloadMailAttachFile(username, mailAttachFile)) {
					String encodedFileName = URLEncoder.encode(mailAttachFile.getName(), "UTF-8").replace("+", "%20");
					
					resp.setContentType("APPLICATION/OCTET-STREAM");
					
					resp.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + encodedFileName);

					FileInputStream fis = new FileInputStream(new File(downloadedMailAttachFilePath));
					
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
					resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    return;
				}
				
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
