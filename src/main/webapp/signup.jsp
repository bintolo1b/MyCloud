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
         <label for="show" class="show-btn">Signup</label>
         <div class="container">
            <label for="show" class="close-btn fas fa-times" title="close"></label>
            <div class="text">
               Login Form
            </div>
            <form action="#" id="form">
               <div class="form-group">
                  <label>Email</label>
                  <input type="text" name="email" rules="required|email" class="form-control">
                  <span class="form-message"></span>
               </div>
               <div class="form-group">
                  <label>Password</label>
                  <input type="password" name="password" rules="required|min:6" class="form-control">
                  <span class="form-message"></span>
               </div>
               <div class="form-group">
                  <label>Verify password</label>
                  <input type="password" name="password" rules="required|min:6" class="form-control">
                  <span class="form-message"></span>
               </div>
               <div class="btn">
                  <div class="inner"></div>
                  <button type="submit">signup</button>
               </div>
               <div class="signup-link">
                  <a href="<c:url value='/login.jsp'/>">Back to login</a>
               </div>
            </form>
         </div>
      </div>
       <!-- Link to the validator.js file -->
      <script src="<c:url value='/assets/js/validator.js'/>"></script>

      <!-- Initialize the Validator -->
      <script>
         document.addEventListener("DOMContentLoaded", function() {
            Validator('#form');
         });
      </script>
   </body>
</html>