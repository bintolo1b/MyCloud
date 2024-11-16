package api.UserAPI;

import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.UserBO;

@WebServlet(urlPatterns = "/admin/getPercentCapacityUsedByUsername")
public class GetPercentCapacityUsedByUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");

		PrintWriter pw = resp.getWriter();
		String username = req.getParameter("username");
		if (username == null) {
			pw.write("{\"erorrMessage\": \"Username is required!\"}");
			return;
		}
		else {
			if (!UserBO.getInstance().doesUserExits(username))
				pw.write("{\"erorrMessage\": \"Username not found!\"}");
			else {
				double percentCapacityUsed = UserBO.getInstance().getPercentCapacityUsedOfAUser(username);
				percentCapacityUsed = UserBO.getInstance().roundToTheFirstDecimal(percentCapacityUsed*100);
				
				double totalCapacityUsed = UserBO.getInstance().getTotalCapacityUsedOfAUser(username)*1.0/(1024*1024*1024);
				totalCapacityUsed = UserBO.getInstance().roundToTheSecondDecimal(totalCapacityUsed);
				
				double totalCapacity = UserBO.getInstance().getMaxCapacityOfAUser(username)*1.0/(1024*1024*1024);
				totalCapacity = UserBO.getInstance().roundToTheSecondDecimal(totalCapacity);
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("percentCapacityUsed", percentCapacityUsed);
				jsonObject.put("totalCapacityUsed", totalCapacityUsed);
				jsonObject.put("totalCapacity", totalCapacity);
				
				resp.getWriter().write(jsonObject.toString());
			}
		}
	}
}
