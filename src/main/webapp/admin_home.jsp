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
	<link rel="stylesheet" href="<c:url value='/assets/css/admin_home.css'/>" />
    <div class="sidenav">
	    <a>
			<img class="home_logo" alt="" src="<c:url value='/assets/img/mycloud_home.png'/>">
		</a>
        <a href="#" class="admin_item active" onclick="showSection('home', this)">
        	<i class="material-icons grey-text text-darken-1">home</i>
        Home</a>
        <a href="#" class="admin_item" onclick="showSection('update-password', this)">
        	<i class="material-icons grey-text text-darken-1">lock_reset</i>
        Update Password</a>
        <a href="#" class="admin_item" onclick="showSection('add-storage', this)">
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
        <!-- Home Section -->
        <div id="home" class="container">
            <h1>List of users</h1>
            <table class="user-table">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Password</th>
                        <th>Fullname</th>
                        <th>Status</th>
                        <th>Capacity</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>nguyenvana</td>
                        <td>5f4dcc3b5aa765d61d8327deb882cf99</td>
                        <td>NguyenVanA</td>
                        <td><span class="status online">Online</span></td>
                        <td>2.5 GB / 10 GB</td>
                    </tr>
                    <tr>
                        <td>nguyenvanb</td>
                        <td>5f4dcc3b5aa765d61d8327deb882cf99</td>
                        <td>NguyenVanB</td>
                        <td><span class="status online">Online</span></td>
                        <td>2.5 GB / 10 GB</td>
                    </tr>
                    <tr>
                        <td>nguyenvanc</td>
                        <td>5f4dcc3b5aa765d61d8327deb882cf99</td>
                        <td>NguyenVanC</td>
                        <td><span class="status offline">Offline</span></td>
                        <td>2.5 GB / 10 GB</td>
                    </tr>
                    <tr>
                        <td>nguyenvand</td>
                        <td>5f4dcc3b5aa765d61d8327deb882cf99</td>
                        <td>NguyenVanD</td>
                        <td><span class="status online">Online</span></td>
                        <td>2.5 GB / 10 GB</td>
                    </tr>
                    <tr>
                        <td>nguyenvane</td>
                        <td>5f4dcc3b5aa765d61d8327deb882cf99</td>
                        <td>NguyenVanE</td>
                        <td><span class="status offline">Offline</span></td>
                        <td>2.5 GB / 10 GB</td>
                    </tr>
                    <tr>
                        <td>nguyenvane</td>
                        <td>5f4dcc3b5aa765d61d8327deb882cf99</td>
                        <td>NguyenVanE</td>
                        <td><span class="status offline">Offline</span></td>
                        <td>2.5 GB / 10 GB</td>
                    </tr>
                    <tr>
                        <td>nguyenvanf</td>
                        <td>5f4dcc3b5aa765d61d8327deb882cf99</td>
                        <td>NguyenVanF</td>
                        <td><span class="status offline">Offline</span></td>
                        <td>2.5 GB / 10 GB</td>
                    </tr>

                    <!-- Other rows -->
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>