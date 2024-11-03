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
	            	<c:forEach items="${mails}" var="mail">
	            	    <c:url value="/userhomepage/mail/readmail" var="readmailurl">
							<c:param name="mailId" value="${mail.id}"></c:param>
						</c:url> 
						 
	            		<tr>	          
							<td class="tr_url" style="display: none">${readmailurl}</td>
		            		<td>${mail.receiverUsername}</td>
			            	<td class="subject-cell" style="width: 900px">${mail.topic}</td>
			            	<td>${mail.formattedSentDate}</td>
		            	</tr>
		           	</c:forEach>		
	            </tbody>
	        </table>
	    </div>
	</div>
	<script src="<c:url value='/assets/js/userHomePage_outbox.js'/>"></script>
</body>
</html>