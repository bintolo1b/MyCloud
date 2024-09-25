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

@WebFilter(urlPatterns = {"/temporary/*"})
public class TemporaryFileFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		if (session!=null && session.getAttribute("username") != null) {
			String username = session.getAttribute("username").toString();
			String url = req.getRequestURL().toString();
			String fileName = url.substring(url.lastIndexOf("/") + 1 , url.length());
			String ownerOfFile = "";
			if (fileName.indexOf("_") != -1)
				ownerOfFile = fileName.substring(0, fileName.indexOf("_"));
			
			if (ownerOfFile.equals(username))
				chain.doFilter(request, response);
			else {
				resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
		}
		else {
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return;
		}
	}
	
}
