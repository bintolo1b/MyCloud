<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>

<%@ page import="constant.AdminAccount" %>

<%
	String username = null;
    if (session != null) {
        username = (String) session.getAttribute("username");
    }
    
    if (username != null) {
    	if (username.equals(AdminAccount.ADMIN_USERNAME)) {
    		response.sendRedirect(request.getContextPath() + "/admin/home");
			return;
		}
    	else{
    		response.sendRedirect(request.getContextPath() + "/userhomepage/main");
    		return;
    	}
	}
%>

<html lang="en" dir="ltr">
   <head>
      <meta charset="utf-8">
      <title>Popup Login Form Design | CodingNepal</title>
      <link href="<c:url value='/assets/css/login.css'/>" rel="stylesheet">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
   	  <link rel="icon" href="<c:url value='/assets/img/logo cloud.png'/>" type="image/x-icon"/>
   </head>
   <body>
   <div id="toast">
    </div>
      <div class="center">
         <input type="checkbox" id="show">
         <label for="show" class="show-btn">Login now!</label>
         <div class="container">
            <label for="show" class="close-btn fas fa-times" title="close"></label>
            <div class="text">
               Login Form
            </div>
            <form id="loginForm">
               <div class="form-group">
                  <label>Username</label>
                  <input id="username" type="text" name="username" required class="form-control">
              	  <span class="form-message"></span>
               </div>
               <div class="form-group">
                  <label>Password</label>
                  <input id="password" type="password" name="passowrd" required class="form-control">
              	  <span class="form-message"></span>
               </div>
               <div class="btn">
                  <div class="inner"></div>
                  <button type="submit">login</button>
               </div>
               <div class="signup-link">
                  Not a member? <a href="<c:url value='/logup'/>">Signup now</a>
               </div>
            </form>
         </div>
      </div>
     <script src="<c:url value='/assets/js/login.js'/>"></script>
   </body>
</html>