<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Personal Information</title>
    <style>
        html, body {
            height: 100%;
            margin: 0;
            font-family: Arial, sans-serif;
        }

        .profile-container {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            text-align: center;
        }

        .profile-container h1 {
            color: #0d47a1;
            font-size: 24px;
            margin-bottom: 20px;
        }

        .profile-card {
            display: flex;
            border: 1px solid #ccc;
            border-radius: 8px;
            padding: 20px;
            background-color: #ffffff;
            width: 800px;
        }

        .profile-left {
            text-align: center;
            border-right: 1px solid #ccc;
            padding-right: 20px;
            margin-right: 20px;
            padding-top: 20px;
        }

        .avatar {
            width: 100px;
            height: 100px;
            border-radius: 50%;
        }

        .name {
            color: #333;
            font-size: 16px;
            margin-top: 10px;
            font-weight: bold;
        }

        .profile-right {
            flex: 1;
            text-align: left;
            padding-top: 20px;
        }

        .profile-info-row {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
            border-bottom: 1px solid #ccc;
            padding-bottom: 5px;
            margin-bottom: 30px;
        }

        .profile-info-row strong {
            color: #0d47a1;
            min-width: 100px;
        }

        .profile-info-row span,
        .profile-info-row input[type="password"] {
            margin-left: 20px;
            color: #555;
            font-size: 16px;
        }

        .profile-info-row input[type="password"] {
            border: none;
            background-color: transparent;
        }
    </style>
</head>
<body>
	<link href="<c:url value='/assets/personalInformation.css'/>" rel="stylesheet">
    <div class="profile-container">
        <h1>PERSONAL INFORMATION</h1>
        <div class="profile-card">
            <div class="profile-left">
                <a href="#!"><img src='<c:url value='/assets/img/user.png'/>' alt="Avatar" class="avatar" /></a>
                <p class="name">user123</p>
            </div>
            <div class="profile-right">
                <div class="profile-info-row">
                    <strong>Full name:</strong>
                    <span>Trần Văn A</span>
                </div>
                <div class="profile-info-row">
                    <strong>Username:</strong>
                    <span>user123</span>
                </div>
                <div class="profile-info-row">
                    <strong>Password:</strong>
                    <input type="password" value="yourpassword" readonly>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
