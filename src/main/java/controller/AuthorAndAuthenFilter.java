package controller;

import java.io.IOException;

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

@WebFilter(urlPatterns = {"/userhomepage/*", "/uploadfilecontroller", "/uploadfoldercontroller", "/deletefoldercontroller", "/deletefilecontroller"
		, "/downloadfilecontroller", "/downloadfoldercontroller", "/gettemporarydemoimgurl"})
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
		if (req.getParameter("folderPath")!=null) {
			String folderPath = req.getParameter("folderPath");
			Folder folder = FolderBO.getInstance().getFolderByPath(folderPath);
			if (folder==null) {
				resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			else if (!FolderBO.getInstance().doesFolderBelongToUser(folder, username)) {
				resp.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this folder.");
				return;
			}
		}
		
		
		chain.doFilter(request, response);
	}

}
