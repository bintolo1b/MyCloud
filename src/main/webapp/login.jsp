<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>

<%
    String username = null;
    if (session != null) {
        username = (String) session.getAttribute("username");
    }
    
    if (username != null) {
        response.sendRedirect(request.getContextPath() + "/userhomepage");
        return;
    }
%>

<html lang="en" dir="ltr">
   <head>
      <meta charset="utf-8">
      <title>Popup Login Form Design | CodingNepal</title>
      <link href="<c:url value='/assets/css/login.css'/>" rel="stylesheet">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
   </head>
   <body>
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
                  <input id="username" type="text" name="username" rules="required" class="form-control">
              	  <span class="form-message"></span>
               </div>
               <div class="form-group">
                  <label>Password</label>
                  <input id="password" type="password" name="passowrd" rules="required|min:6" class="form-control">
              	  <span class="form-message"></span>
               </div>
               <div class="forgot-pass">
                  <a href="#">Forgot Password?</a>
               </div>
               <div class="btn">
                  <div class="inner"></div>
                  <button type="submit">login</button>
               </div>
               <div class="signup-link">
                  Not a member? <a href="<c:url value='/signup.jsp'/>">Signup now</a>
               </div>
            </form>
         </div>
      </div>
      <!-- Link to the validator.js file -->
      <script src="<c:url value='/assets/js/validator.js'/>"></script>

      <!-- Initialize the Validator -->
      <!-- <script>
         document.addEventListener("DOMContentLoaded", function() {
            Validator('#loginForm');
         });
      </script> -->
      
      <script src="<c:url value='/assets/js/login.js'/>"></script>
   </body>
</html>
