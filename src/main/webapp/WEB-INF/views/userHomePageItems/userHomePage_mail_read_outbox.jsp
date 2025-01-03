<%@include file="/common/taglib.jsp"%>

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
				<c:url value="/downloadmailattachfilecontroller" var="downloadattachfileurl">
					<c:param name="mailAttachFilePath" value="${mailAttachFile.path}"></c:param>
				</c:url>
				<a href="${downloadattachfileurl}">
					<img class="fileDownload" src='<c:url value='/assets/img/fileDownload.png'/>'/>
					${mailAttachFile.name}
				</a>
				</div>
		</c:forEach>
		<a id="back-button" href="<c:url value='/userhomepage/outbox'/>">Back to outbox</a>
	</div>
</div>
