<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>

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
            <form action="#">
               <div class="data">
                  <label>Username</label>
                  <input type="text" required>
               </div>
               <div class="data">
                  <label>Password</label>
                  <input type="password" required>
               </div>
               <div class="forgot-pass">
                  <a href="#">Forgot Password?</a>
               </div>
               <div class="btn">
                  <div class="inner"></div>
                  <button type="submit">login</button>
               </div>
               <div class="signup-link">
                  Not a member? <a href="#">Signup now</a>
               </div>
            </form>
         </div>
      </div>
   </body>
</html>