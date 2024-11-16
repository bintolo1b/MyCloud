package api.UserAPI;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.UserBO;

@WebServlet(urlPatterns = "/getPercentCapacityUsed")
public class GetPercentCapacityUsed extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		String username = req.getSession().getAttribute("username").toString();
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
