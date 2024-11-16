package api;

import java.io.IOException;

import org.json.JSONObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.bo.UserBO;

@WebServlet(urlPatterns = "/admin/getPercentCapacityUsedOfCloud")
public class GetPercentCapacityUsedOfCloud extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		double percentCloudCapacityUsed = UserBO.getInstance().getPercentCapacityUsedOfCloud();
		percentCloudCapacityUsed = UserBO.getInstance().roundToTheFirstDecimal(percentCloudCapacityUsed*100);
		
		double totalCloudCapacityUsed = UserBO.getInstance().getToTalCapacityAllocatedForUser()*1.0/(1024*1024*1024);
		totalCloudCapacityUsed = UserBO.getInstance().roundToTheSecondDecimal(totalCloudCapacityUsed);
		
		double totalCloudCapacity = UserBO.getInstance().getTotalCapacityOnDisk()*1.0/(1024*1024*1024);
		totalCloudCapacity = UserBO.getInstance().roundToTheSecondDecimal(totalCloudCapacity);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("percentCloudCapacityUsed", percentCloudCapacityUsed);
		jsonObject.put("totalCloudCapacityUsed", totalCloudCapacityUsed);
		jsonObject.put("totalCloudCapacity", totalCloudCapacity);
		
		resp.getWriter().write(jsonObject.toString());
	}
}
