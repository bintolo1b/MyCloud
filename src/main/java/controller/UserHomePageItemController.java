package controller;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@WebServlet("/userhomepageitem")
public class UserHomePageItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final List<String> VALID_PAGES = Arrays.asList(
    	    "userHomePageItems/myDriveItem.jsp", 
    	    "userHomePageItems/computerItem.jsp", 
    	    "userHomePageItems/sharedItem.jsp",
    	    "userHomePageItems/recentItem.jsp", 
    	    "userHomePageItems/googlePhotoItem.jsp", 
    	    "userHomePageItems/starredItem.jsp", 
    	    "userHomePageItems/trashItem.jsp", 
    	    "userHomePageItems/backUpItem.jsp", 
    	    "userHomePageItems/upgradeStorageItem.jsp"
    	);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("page");

        if (page == null || page.isEmpty()) {
            page = "defaultPage.jsp"; 
        }	

        if (VALID_PAGES.contains(page)) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/" + page);
            dispatcher.forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Trang không tồn tại");
        }
    }

}
