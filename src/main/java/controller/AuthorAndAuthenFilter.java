package controller;

import java.io.IOException;

import constant.AdminAccount;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.bean.Folder;
import model.bo.FolderBO;

@WebFilter(urlPatterns = {"/userhomepage/*", "/admin/*", "/uploadfile", "/uploadfolder", "/deletefoldercontroller", "/deletefilecontroller"
		, "/downloadfilecontroller", "/downloadfoldercontroller", "/gettemporarydemoimgurl","/sendmail", "/createnewfolder" ,"/renamefile" 
		, "/renamefolder","/downloadmailattachfilecontroller", "/search" , "/userinformation", "/updatePassword" ,"/updateAvatar"
		, "/updateFullName", "/getPercentCapacityUsed" ,"/displayfilecontroller", "/checkreadnotification", "/checkifuploadpossible"})
public class AuthorAndAuthenFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("username") == null) {
			resp.sendRedirect(req.getContextPath());
			return;
		}
		
		String username = session.getAttribute("username").toString();
		String requestURI = req.getRequestURI();

		if (requestURI.startsWith(req.getContextPath() + "/admin/")) {
			if (!AdminAccount.ADMIN_USERNAME.equals(username)) {
				resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Source for admin only.");
				return;
			}
		} else {
			if (AdminAccount.ADMIN_USERNAME.equals(username)) {
				resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Source for user only.");
				return;
			}
		}
		
		try {
			
		} catch (Exception e) {
			System.out.println("here");
			e.printStackTrace();
		}
		if (req.getParameter("folderPath")!=null) {
			String folderPath = req.getParameter("folderPath");
			Folder folder = FolderBO.getInstance().getFolderByPath(folderPath);
			if (folder==null) {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			else if (!folder.getOwnerUsername().equals(username)) {
				resp.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this folder.");
				return;
			}
		}
		
		
		chain.doFilter(request, response);
	}

}
