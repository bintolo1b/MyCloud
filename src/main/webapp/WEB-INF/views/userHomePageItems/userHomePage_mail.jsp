<!DOCTYPE html>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mail Page</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
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
</body>
</html>
