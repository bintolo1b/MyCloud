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
<link href="<c:url value='/assets/css/style.css'/>" rel="stylesheet">
</head>
<body>
	<h1>Welcome to google drive clone</h1>
	<a href="<%=request.getContextPath()%>/FileController">Home folders</a>
	<h1>Current Folder - ${path}</h1>
	<h2>List of folders</h2>
	<c:forEach items = "${folders}" var = "folder">
		<form action="<%=request.getContextPath()%>/FileController" method="get">
			<input type="hidden" name="path" value="${path}/${folder}"/>
			<input type="submit" value="${folder}"/>
		</form>
		<c:url value="/FolderController" var="deleteurl">
			<c:param name="path" value="${path}"></c:param>
			<c:param name="action" value="delete"></c:param>
			<c:param name="folder" value="${folder}"></c:param>
		</c:url>
		<a href="${deleteurl}">Delete Folder</a>
		<br>
	</c:forEach>
	<br>
	
	<form action="<%=request.getContextPath()%>/FolderController" method="get">
			<input type="hidden" name="path" value="${path}"/>
			<input type="hidden" name="action" value="create"/>
			<input type="text" name="folder" placeholder="Enter your folder name"/>
			<input type="submit" value="Create folder"/>
	</form>
	
	<h1>List of files</h1>
	<c:forEach items="${files}" var = "file">
		<c:url value="/DownloadController" var="downloadurl">
			<c:param name="path" value="${path}"></c:param>
			<c:param name="file" value="${file}"></c:param>
		</c:url>
		<a href="${downloadurl}">${file}</a>
		<br>
	</c:forEach>
	<br>
	<br>
	<c:url value="/UploadController" var="uploadurl">
		<c:param name="path" value="${path}"></c:param>
	</c:url>
	<form action="${uploadurl}" method="post" enctype="multipart/form-data">
		select file:
		<input type="file" name="files" multiple/>
		<input type="submit" value="Upload file"/>
	</form>
</body>
</html>