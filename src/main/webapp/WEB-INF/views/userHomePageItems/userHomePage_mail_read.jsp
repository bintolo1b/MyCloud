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
	<div id="email-details" class="email-details">
            <p id="email-sender">${mail.senderUsername}</p>
            <p id="email-subject">${mail.topic}</p>
            <p id="email-content">${mail.content}</p>
			<c:forEach items="${mailAttachFiles}" var="mailAttachFile">
				 <div id="email-attachment">
            	 	<a href="#">${mailAttachFile.name}</a>
            	 </div>
			</c:forEach>
            <p id="email-date">${mail.formattedSentDate}</p>
            <a href="<c:url value='/userhomepage/mail'/>">Back to inbox</a>
     </div>
</body>
</html>