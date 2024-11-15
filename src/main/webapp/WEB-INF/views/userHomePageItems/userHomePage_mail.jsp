<%@include file="/common/taglib.jsp"%>

<link href="<c:url value='/assets/css/userHomePage_mail.css'/>" rel="stylesheet">
<div class="main">
	<div class="inbox-container">
		<table id="inbox-table">
			<tbody id="inbox-body">
				<c:forEach items="${mails}" var="mail">	     
					<c:url value="/userhomepage/mail/readmail" var="readmailurl">
						<c:param name="mailId" value="${mail.id}"></c:param>
					</c:url>   
											
					<c:if test="${mail.status == 'Pending'}">
						<tr class="unread">	          
					</c:if>
					
					<c:if test="${mail.status == 'Read'}">
						<tr class="read">	           
					</c:if>
						<td class="tr_url" style="display: none">${readmailurl}</td>
						<td>${mail.senderUsername}</td>
						<td class="subject-cell" style="width: 75%">${mail.topic}</td>
						<td>${mail.formattedSentDate}</td>
					</tr>		            
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<script src="<c:url value='/assets/js/userHomePage_mail.js'/>"></script>
