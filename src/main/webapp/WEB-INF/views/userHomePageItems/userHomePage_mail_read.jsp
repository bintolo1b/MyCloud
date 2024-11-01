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
		<div id="email-details" class="email-details">
            <p id="email-subject">${mail.topic}</p>
            <div id="sender-container">
				<a href="#!"><img src='<c:url value='/assets/img/user.png'/>' alt="profile pic" class="accountAvt" /></a> 	
	            <p id="email-sender">${mail.senderUsername}</p>
	            <p id="email-date">${mail.formattedSentDate}</p>
            </div>
            <p id="email-content">${mail.content}</p>
			<c:forEach items="${mailAttachFiles}" var="mailAttachFile">
				 <div id="email-attachment">
            	 	<a href="#">
	            	 	<img class="fileDownload" src='<c:url value='/assets/img/fileDownload.png'/>'/>
	            	 	${mailAttachFile.name}
            	 	</a>
            	 </div>
			</c:forEach>
            <a id="back-button" href="<c:url value='/userhomepage/mail'/>">Back to inbox</a>
     </div>
	</div>
</body>
</html>