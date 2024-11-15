<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet"
		href="https://fonts.googleapis.com/icon?family=Material+Icons" />
    <title>Quản lý tài khoản</title>
</head>
<body>
	<link rel="stylesheet" href="<c:url value='/assets/css/admin.css'/>" />
    <div class="sidenav">
        <a href="#" class="active" onclick="showSection('home', this)">
        	<i class="material-icons grey-text text-darken-1">home</i>
        Home</a>
        <a href="#" onclick="showSection('requests', this)">
        	<i class="material-icons grey-text text-darken-1">schedule_send</i>
        Request</a>
        <a href="#" onclick="showSection('update-password', this)">
        	<i class="material-icons grey-text text-darken-1">lock_reset</i>
        Update Password</a>
        <a href="#" onclick="showSection('add-storage', this)">
        <i class="material-icons grey-text text-darken-1">memory</i>
        Add Storage</a>
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
        
        <!-- Request Section -->
        <div id="requests" class="container hidden">
            <h1>Request to reset password from users</h1>
            <table class="request-table">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Fullname</th>
                        <th>Datetime</th>
                        <th>Reset time</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>nguyenvana</td>
                        <td>NguyenVanA</td>
                        <td>2024-11-09 14:30</td>
                        <td>2</td>
                        <td><button class="accept-button">Accept</button></td>
                    </tr>
                    <!-- Other rows -->
                </tbody>
            </table>
        </div>

        <!-- Update Password Section -->
        <div id="update-password" class="container hidden">
            <h1>Update User Password</h1>
            <form class="update-password-form">
                <div class="form-control">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" onblur="showPasswordInputs()" required>
                </div>

                <div id="password-inputs" class="hidden">
                    <div class="form-control">
                        <label for="new-password">New Password:</label>
                        <input type="password" id="new-password" name="new-password" required>
                    </div>

                    <div class="form-control">
                        <label for="confirm-password">Verify New Password:</label>
                        <input type="password" id="confirm-password" name="confirm-password" required>
                    </div>

                    <button type="submit" class="save-password-change">Save Changes</button>
                </div>
            </form>
        </div>

        <div id="add-storage" class="container hidden">
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
	<script src="<c:url value='/assets/js/admin.js'/>"></script>
   
</body>
</html>
