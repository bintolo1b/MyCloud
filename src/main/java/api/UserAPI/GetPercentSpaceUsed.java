package api.UserAPI;

import java.io.IOException;

import org.json.JSONObject;

import constant.Server;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.UserBO;

@WebServlet(urlPatterns = "/getPercentSpaceUsed")
public class GetPercentSpaceUsed extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		String username = req.getSession().getAttribute("username").toString();
		double percentSpaceUsed = UserBO.getInstance().getPercetSpaceUsedOfAUser(username);
		percentSpaceUsed = UserBO.getInstance().roundToTheFirstDecimal(percentSpaceUsed*100);
		
		double totalSizeUsed = UserBO.getInstance().getTotalSizeUsedOfAUser(username)*1.0/(1024*1024*1024);
		totalSizeUsed = UserBO.getInstance().roundToTheSecondDecimal(totalSizeUsed);
		
		double totalSize = Server.SIZE_FOR_A_USER*1.0/(1024*1024*1024);
		totalSize = UserBO.getInstance().roundToTheSecondDecimal(totalSize);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("percentSpaceUsed", percentSpaceUsed);
		jsonObject.put("totalSizeUsed", totalSizeUsed);
		jsonObject.put("totalSize", totalSize);
		
		resp.getWriter().write(jsonObject.toString());
	}
}
