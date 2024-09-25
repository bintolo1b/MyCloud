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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/loadPage")
public class LoadPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoadPageServlet.class.getName());
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
            logger.log(Level.INFO,page);
            dispatcher.forward(request, response);
        } else {
            logger.log(Level.WARNING, "Requested page not found: " + page);
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Trang không tồn tại");
        }
    }

}
