<!DOCTYPE html>
<%@include file="/common/taglib.jsp"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mail Page</title>
    <link href="<c:url value='/assets/css/userHomePage_mail.css'/>" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  
</head>
<body>
	<div class="main">
	    <div class="inbox-container">
	        <table id="inbox-table">
	            <tbody id="inbox-body">
	            </tbody>
	        </table>
	    </div>
	    
	    <div id="email-details" class="email-details">
            <p id="email-sender"></p>
            <p id="email-subject"></p>
            <p id="email-date"></p>
            <p id="email-content"></p>
            <button id="back-button">Back to Inbox</button>
        </div>
	</div>
		
    <script src="<c:url value='/assets/js/userHomePage_mail.js'/>"></script>
</body>
</html>
