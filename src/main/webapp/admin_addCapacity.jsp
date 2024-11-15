<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<link rel="stylesheet"
		href="https://fonts.googleapis.com/icon?family=Material+Icons" />
<title>Insert title here</title>
</head>
<body>
	<link rel="stylesheet" href="<c:url value='/assets/css/admin_menu.css'/>" />
	<link rel="stylesheet" href="<c:url value='/assets/css/admin_addCapacity.css'/>" />
    <div class="sidenav">
    	<a>
			<img class="home_logo" alt="" src="<c:url value='/assets/img/mycloud_home.png'/>">
		</a>
        <a href="#" class="admin_item" onclick="showSection('home', this)">
        	<i class="material-icons grey-text text-darken-1">home</i>
        Home</a>
        <a href="#" class="admin_item" onclick="showSection('update-password', this)">
        	<i class="material-icons grey-text text-darken-1">lock_reset</i>
        Update Password</a>
        <a href="#" class="admin_item active" onclick="showSection('add-storage', this)">
        	<i class="material-icons grey-text text-darken-1">memory</i>
        Add Storage</a>
        
        <form action="/logout" method="POST" class="logout_form">
	        <button type="submit" class="logout_button">
	            <i class="material-icons grey-text text-darken-1">logout</i>
	            Logout
	        </button>
    	</form>
    </div>
    <div class="main-content">
        <div id="add-storage" class="container">
            <h1>Add Storage for User</h1>
            <form class="add-storage-form">
                <div class="form-control">
                    <label for="storage-username">Username:</label>
                    <input type="text" id="storage-username" name="storage-username" onblur="showUserDetails()" required>
                </div>
                <div id="storage-inputs" class="hidden">
                    <div class="form-control" id="user-details">
                        <label>User Name:</label>
                        <input id="user-fullname" disabled>
                    </div>

                    <div class="form-control" id="current-storage">
                        <label>Current Storage:</label>
                        <input id="storage-capacity" disabled>
                    </div>

                    <div class="form-control" id="add-storage-amount">
                        <label for="additional-storage">Additional Storage (GB):</label>
                        <input type="number" id="additional-storage" name="additional-storage" min="0" required>
                    </div>

                    <button type="submit" class="save-storage-change">Save Changes</button>
                </div>
            </form>
        </div>
    </div>
	<script src="<c:url value='/assets/js/admin_addCapacity.js'/>"></script>
</body>
</html>