<!DOCTYPE html>
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
	</div>
	
	 <script src="<c:url value='/assets/js/userHomePage_mail.js'/>"></script>

    <script type="text/javascript">
    (function() {
        const inboxData = [
            { sender: "Google", subject: "Welcome to Gmail!", date: "Oct 5, 2024", content: "Welcome to Gmail! We're glad to have you onboard.", isRead: false },
            { sender: "GitHub", subject: "New Pull Request", date: "Oct 4, 2024", content: "A new pull request has been submitted to your repository.", isRead: true },
            { sender: "Amazon", subject: "Your Order has Shipped", date: "Oct 3, 2024", content: "Your order has been shipped. You can track it here.", isRead: false },
            { sender: "Netflix", subject: "New Movies this Week", date: "Oct 2, 2024", content: "Check out the new movies and shows added this week on Netflix.", isRead: true },
            { sender: "Spotify", subject: "Your Playlist Summary", date: "Oct 1, 2024", content: "Here's a summary of your most played songs this month.", isRead: false },
            { sender: "LinkedIn", subject: "New Connection Request", date: "Sep 30, 2024", content: "You have a new connection request from a colleague.", isRead: true }
        ];

        function loadInbox() {
            const inboxTableBody = document.getElementById('inbox-body');
            inboxTableBody.innerHTML = ''; // Clear any existing content
            inboxData.forEach((email, index) => {
                const row = document.createElement('tr');
                row.classList.add(email.isRead ? 'read' : 'unread');

                const senderCell = document.createElement('td');
                senderCell.textContent = email.sender;

                const subjectCell = document.createElement('td');
                subjectCell.textContent = email.subject;
                subjectCell.classList.add('subject-cell');

                const dateCell = document.createElement('td');
                dateCell.textContent = email.date;

                row.appendChild(senderCell);
                row.appendChild(subjectCell);
                row.appendChild(dateCell);

                row.addEventListener('click', () => {
                    markAsRead(index, row);
                    displayEmailDetails(index);
                });

                inboxTableBody.appendChild(row);
            });
        }

        function markAsRead(index, row) {
            inboxData[index].isRead = true; 
            row.classList.remove('unread');
            row.classList.add('read'); 
            console.log('Email marked as read');
        }

        function displayEmailDetails(index) {
            const email = inboxData[index];
            document.getElementById('email-sender').textContent = "Sender: " + email.sender;
            document.getElementById('email-subject').textContent = "Subject: " + email.subject;
            document.getElementById('email-date').textContent = "Date: " + email.date;
            document.getElementById('email-content').textContent = email.content;
            
            document.getElementById('email-details').classList.add('visible');
            document.querySelector('.inbox-container').style.display = 'none'; // Hide inbox
        }

        document.getElementById('back-button').addEventListener('click', () => {
            document.getElementById('email-details').classList.remove('visible');
            document.querySelector('.inbox-container').style.display = 'block'; // Show inbox again
        });

        loadInbox();
    })();
    </script>
</body>
</html>
