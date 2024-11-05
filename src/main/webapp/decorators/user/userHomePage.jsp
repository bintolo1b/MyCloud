<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html> 
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" />
	<link rel="stylesheet"
		href="https://fonts.googleapis.com/icon?family=Material+Icons" />
	<link rel="stylesheet" href="<c:url value='/assets/css/userHomePage.css'/>" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<link href="https://fonts.googleapis.com/css2?family=Google+Sans:wght@400;500;700&display=swap" rel="stylesheet">
	<link rel="icon" href="path/to/favicon.ico" type="image/x-icon">
	<link rel="icon" href="<c:url value='/assets/img/logo cloud.png'/>" type="image/x-icon"/>
	<title>My cloud</title>
</head>	
<body>	
	<%@include file="/common/user/header.jsp"%>
	<%@include file="/common/user/menu.jsp"%>
	<script src="<c:url value='/assets/js/userHomePage_menu.js'/>"></script>
	<script src="<c:url value='/assets/js/userHomePage_header.js'/>"></script>
	<dec:body/>
</body>
</html>