<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>MyCloud Admin</title>
	<link rel="stylesheet" href="<c:url value='/assets/css/admin_menu.css'/>" />
	<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons" />
</head>
<body>
	<%@include file="/common/admin/menu.jsp"%>
	<script src="<c:url value='/assets/js/admin_menu.js'/>"></script>
	<dec:body/>
</body>
</html>