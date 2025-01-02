<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Personal Information</title>
<link rel="stylesheet"
		href="https://fonts.googleapis.com/icon?family=Material+Icons" />
<link rel="icon" href="<c:url value='/assets/img/logo cloud.png'/>" type="image/x-icon"/>
</head>
<body>
	<link href="<c:url value='/assets/css/personalInformation.css'/>" rel="stylesheet">
    <div class="profile-container">
        <h1>PERSONAL INFORMATION</h1>
        <div class="profile-card">
            <div class="profile-left">
            	<div class="avatar-container">
		            <img id="avatar" alt="" src="/PBL4/getAvatar?username=${user.username}">
		            <input type="file" id="avatar-upload" accept="image/*" onchange="updateAvatar(event)">
		            <label for="avatar-upload" class="upload-overlay">Cập nhật ảnh</label>
		        </div>
                <p class="name">${user.fullName}</p>
            </div>
            <div class="profile-right">
                <div class="profile-info-row">
                    <strong>Full name:</strong>
                    <span>${user.fullName}</span>
                    <i class="material-icons edit-icon" onclick="updateFullName()">edit</i>
                </div>
                <div class="profile-info-row">
                    <strong>Username:</strong>
                    <span>${user.username}</span>
                </div>
                <div class="profile-info-row">
                    <strong>Password:</strong>
                    <span>************</span>
                    <i class="material-icons edit-icon" onclick="changePassword()">edit</i>
                </div>
            </div>
        </div>
    </div>
    <div class="update-container">
    <!-- Form Update Fullname -->
    <form action="" method="POST" class="update-form" id="update-name-form">
        <div class="update-fullname update-card">
            <h1>UPDATE FULLNAME</h1>
            <div class="update-row">
                <label><strong>Your fullname:</strong></label>
                <input id = "fullName-Text" type="text" class="update-input" required />
            </div>
            <button type="submit" class="save-button save-fullname-button">Save change</button>
        </div>
    </form>

    <!-- Form Change Password -->
    <form action="" method="POST" class="update-form" id="update-password-form">
        <div class="update-password update-card">
            <h1>CHANGE PASSWORD</h1>
            <div class="update-row">
                <label><strong>Your current password:</strong></label>
                <input type="password" class="update-input" id="currentPassword" required />
            </div>
            <div class="update-row">
                <label><strong>Your new password:</strong></label>
                <input type="password" class="update-input" id="newPassword" required />
            </div>
            <div class="update-row">
                <label><strong>Verify your new password:</strong></label>
                <input type="password" class="update-input" id="verifyNewPassword" required />
            </div>
            <button type="submit" class="save-button save-new-password-button">Save change</button>
        </div>
    </form>
</div>


</body>
<script src="<c:url value='/assets/js/personalInformation.js'/>">
</script>
</html>