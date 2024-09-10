<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>welcome to google drive clone</h1>
	<a href="<%=request.getContextPath()%>/FileController">Start Browsing your files</a>
	<c:url value="/logout" var="logouturl">
	</c:url>
	<form action="${logouturl}" method="post">
		<button type="submit">logout</button>
	</form>
</body>
</html>