package controller.MailController;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.Mail;
import model.bean.MailAttachFile;
import model.bo.MailAttachFileBO;
import model.bo.MailBO;

@WebServlet(urlPatterns = "/userhomepage/mail/readreceivedmail")
public class ReadReceivedMailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		String username = session.getAttribute("username").toString();
		if (req.getParameter("mailId")!=null) {
			Integer mailId = null;
			try {
				mailId =  Integer.parseInt(req.getParameter("mailId"));
			}catch (Exception e) {
				e.printStackTrace();
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			if (mailId!=null) {
				Mail mail = MailBO.getInstance().getMailById(mailId);
				if (mail == null) {
					resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
					return;
				}
				else if (mail!=null && !mail.getReceiverUsername().equals(username)) {
					resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
				}
				else if (mail!=null && mail.getReceiverUsername().equals(username)){
					ArrayList<MailAttachFile> mailAttachFiles = MailAttachFileBO.getInstance().getAllMailAttachFileOfMail(mailId);
					
					req.setAttribute("mailAttachFiles", mailAttachFiles);
					req.setAttribute("mail", mail);
					RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/userHomePageItems/userHomePage_mail_read.jsp");
					requestDispatcher.forward(req, resp);
				}
			}
		}
		else {
			resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
	}
}
