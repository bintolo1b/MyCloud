<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Start Chat</h1>
	<div id="message">
		
	</div>
	
	<input type="text">
	<button type="send"></button>
	<script type="">
		let socket = new WebSocket("ws://localhost:8080/PBL4/chat");
	 	socket.onopen = function(){
   		 	document.querySelectorAll("h1")[0].innerText = "Connect successfully"
		}
		
		socket.onmessage = new function(event){
		    var messagediv = document.getElementById("#message");
 		    var newmessagediv = document.createElement("div");
			newmessagediv.textContent = "Client1 :"+event.data;
			messagediv.appendChild(newmessagediv);
		}
		
	</script>
</body>
</html>