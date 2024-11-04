<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<link href="<c:url value='/assets/css/userHomePage_mail.css'/>" rel="stylesheet">
	<div class="main">
	    <div class="outbox-container">
	        <table id="out-table">
	            <tbody id="outbox-body">
	            		<tr class="read">	           
		            		<td>To</td>
			            	<td class="subject-cell" style="width: 75%">Topic</td>
			            	<td>Datetime</td>
		            	</tr>		            
	            </tbody>
	        </table>
	    </div>
	</div>
</body>
</html>