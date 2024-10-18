<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="message">
		
	</div>
	
	<input type="text" id="messageInput">
	<button type="send" onclick="sendMessage()"></button>
	<script type="">
		let socket = new WebSocket("ws://localhost:8080/PBL4/chat");
	 	socket.onopen = function(){
   		 	document.querySelectorAll("h1")[0].innerText = "Connect successfully"
		}
		
		socket.onmessage = new function(event){
		    var messagediv = document.getElementById("#message");
 		    var newmessagediv = document.createElement("div");
			newmessagediv.textContent = "Client2 :"+event.data;
			messagediv.appendChild(newmessagediv);
		}

		function sendMessage() {
            let messageInput = document.getElementById("messageInput");
            let message = messageInput.value;
            if (socket.readyState === WebSocket.OPEN) {
                socket.send(message);
                messageInput.value = "";
            } else {
                console.log("WebSocket chưa sẵn sàng.");
            }
        }

		window.addEventListener('beforeunload', evt => {
        let result = dialog.showMessageBox({
            message: 'Quit app?',
            buttons: ['Yes', 'No']
        })

        if (result == 1) evt.returnValue = false
    	})
		
	</script>
</body>
</html>