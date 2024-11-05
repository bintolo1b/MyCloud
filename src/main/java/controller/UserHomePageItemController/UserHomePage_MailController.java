package controller.UserHomePageItemController;

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
import model.bo.MailBO;

@WebServlet(urlPatterns = "/userhomepage/mail")
public class UserHomePage_MailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session!=null && session.getAttribute("username")!=null) {
			String username = session.getAttribute("username").toString();
			ArrayList<Mail> mails = MailBO.getInstance().getAllReceivedMail(username);
			req.setAttribute("mails", mails);
			
			RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/userHomePageItems/userHomePage_mail.jsp");
			requestDispatcher.forward(req, resp);
		}
	}
}