<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
		href="https://fonts.googleapis.com/icon?family=Material+Icons" />
</head>
<body>
	<link href="<c:url value='/assets/css/personalInformation.css'/>" rel="stylesheet">
    <div class="profile-container">
        <h1>PERSONAL INFORMATION</h1>
        <div class="profile-card">
            <div class="profile-left">
            	<div class="avatar-container">
		            <img id="avatar" src="data:image/jpeg;base64,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" alt="Avatar">
		            <input type="file" id="avatar-upload" accept="image/*" onchange="updateAvatar(event)">
		            <label for="avatar-upload" class="upload-overlay">Cập nhật ảnh</label>
		        </div>
                <p class="name">user123</p>
            </div>
            <div class="profile-right">
                <div class="profile-info-row">
                    <strong>Full name:</strong>
                    <span>Tran Van A</span>
                    <i class="material-icons edit-icon" onclick="updateFullName()">edit</i>
                </div>
                <div class="profile-info-row">
                    <strong>Username:</strong>
                    <span>user123</span>
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
	    <form action="" method="POST" class="update-form">
	        <div class="update-fullname update-card">
	            <h1>UPDATE FULLNAME</h1>
	            <div class="update-row">
	                <label><strong>Your fullname:</strong></label>
	                <input type="text" name="fullname" class="update-input" required />
	            </div>
	        </div>
	
	        <div class="update-password update-card">
	            <h1>CHANGE PASSWORD</h1>
	            <div class="update-row">
	                <label><strong>Your current password:</strong></label>
	                <input type="password" name="currentPassword" class="update-input" required />
	            </div>
	            <div class="update-row">
	                <label><strong>Your new password:</strong></label>
	                <input type="password" name="newPassword" class="update-input" required />
	            </div>
	            <div class="update-row">
	                <label><strong>Verify your new password:</strong></label>
	                <input type="password" name="verifyNewPassword" class="update-input" required />
	            </div>
	        </div>
	
	        <button type="submit" class="save-button">Save changes</button>
	    </form>
	</div>

</body>
<script>
        function updateAvatar(event) {
	        const avatar = document.getElementById('avatar');
	        const file = event.target.files[0];
	        if (file) {
	            const reader = new FileReader();
	            reader.onload = function (e) {
	                avatar.src = e.target.result;
	            };
	            reader.readAsDataURL(file);
	        }
	    }
        
        function updateFullName() {
        	document.querySelector('.update-fullname').style.display = 'block';
        	document.querySelector('.save-button').style.display = 'block';
        }
        
        function changePassword() {
        	document.querySelector('.update-password').style.display = 'block';
        	document.querySelector('.save-button').style.display = 'block';
        }
</script>
</html>