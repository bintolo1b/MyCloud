package api.MailAPI;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import model.bo.MailBO;
import model.bo.UserBO;

@WebServlet(urlPatterns = "/sendmail")
@MultipartConfig
public class SendMail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		PrintWriter pw = resp.getWriter();
		
		try {
			HttpSession session = req.getSession(false);
			String senderUsername = session.getAttribute("username").toString();
			String receiverUsername = req.getParameter("receiverUsername");
			String topic = req.getParameter("topic");
			String content = req.getParameter("content");
			
			Collection<Part> parts = req.getParts();
			
			if (UserBO.getInstance().checkIfEnoughCapacityToUpload(senderUsername, parts) == false){
				pw.write("{\"message\": \"Not enough space to store mail attach file!\"}");
                return;
			}
			
			Integer[] returnMailIdIfSuccess = new Integer[1];
			String message = MailBO.getInstance().sendMail(senderUsername, receiverUsername, topic, content, parts, returnMailIdIfSuccess);
			
			if (message.equals("Sent Successfully!")) {
				pw.write("{\"message\": \"" + message + "\", \"mailId\": " + returnMailIdIfSuccess[0] + "}");
			}
			else
				pw.write("{\"message\": \"" + message + "\"}");
			
		}
		catch (Exception e) {
			e.printStackTrace();
			pw.write("{\"message\": \"Exception occur!\"}");
		}
	}
}
