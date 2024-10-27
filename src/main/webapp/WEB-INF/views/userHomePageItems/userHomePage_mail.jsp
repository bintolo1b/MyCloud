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
	<div class="main">
	    <div class="inbox-container">
	        <table id="inbox-table">
	            <tbody id="inbox-body">
	            	<c:forEach items="${mails}" var="mail">
	            		<c:if test="${mail.status == 'Pending'}">
	            			<tr class="unread">	          
	            		</c:if>
	            		<c:if test="${mail.status == 'Read'}">
	            			<tr class="unread">	           
	            		</c:if>
		            		<td>${mail.senderUsername}</td>
			            	<td class="subject-cell" style="width: 900px">${mail.topic}</td>
			            	<td>${mail.formattedSentDate}</td>
		            	</tr>
	            	</c:forEach>
	            </tbody>
	        </table>
	    </div>
	    
	    <div id="email-details" class="email-details">
            <p id="email-sender"></p>
            <p id="email-subject"></p>
            <!-- <p id="email-content"></p>
            <div id="email-attachment"></div> -->
            <p id="email-date"></p>
            <button id="back-button">Back to Inbox</button>
        </div>
	</div>
		
   <script src="<c:url value='/assets/js/userHomePage_mail.js'/>"></script>
</body>
</html>
