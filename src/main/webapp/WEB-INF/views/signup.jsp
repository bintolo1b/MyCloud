<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>

<html lang="en" dir="ltr">
   <head>
      <meta charset="utf-8">
      <title>Popup Signup Form Design | CodingNepal</title>
      <link href="<c:url value='/assets/css/login.css'/>" rel="stylesheet">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>
      <link rel="icon" href="<c:url value='/assets/img/logo cloud.png'/>" type="image/x-icon"/>
   </head>
   <body>
   <div id="toast"></div>
      <div class="center">
         <input type="checkbox" id="show" style="display: none;">
		 <label for="show" class="show-btn">Show</label>
         <div class="container">
            <label for="show" class="close-btn fas fa-times" title="close"></label>
            <div class="text">
               Signup Form
            </div>
            <form action="#" id="form">
               <div class="form-group">
                  <label>Username</label>
                  <input id="username" type="text" name="email" rules="required|email" class="form-control">
                  <span class="form-message"></span>
               </div>
               <div class="form-group">
                  <label>Full name</label>
                  <input id="fullName" type="text" name="fullName" required class="form-control">
              	  <span class="form-message"></span>
               </div>
               <div class="form-group">
                  <label>Password</label>
                  <input id="password" type="password" name="password" rules="required|min:6" class="form-control">
                  <span class="form-message"></span>
               </div>
               <div class="form-group">
                  <label>Verify password</label>
                  <input id="verifyPassword" type="password" name="password" rules="required|min:6" class="form-control">
                  <span class="form-message"></span>
               </div>
               <div class="btn">
                  <div class="inner"></div>
                  <button type="submit">signup</button>
               </div>
               <div class="signup-link">
                  <a href="<c:url value='/login'/>">Back to login</a>
               </div>
            </form>
         </div>
      </div>
      <script src="<c:url value='/assets/js/logup.js'/>"></script>
       <!-- Link to the validator.js file -->

      <!-- Initialize the Validator -->
      <script>
         document.addEventListener("DOMContentLoaded", function() {
            Validator('#form');
         });
         
         var checkbox = document.getElementById('show');
         
         checkbox.checked = true;

         if (checkbox.checked) {
           document.querySelector('.container').style.display = 'block';
           document.querySelector('.show-btn').style.display = 'none';
           console.log(checkbox);
         }
      </script>
   </body>
</html>