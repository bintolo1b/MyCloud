<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Mail Page</title>
    <style>
        .inbox-container {
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
            text-align: left;
            vertical-align: middle;
            font-size: 14px;
        }

        td:last-child {
            text-align: right;
            white-space: nowrap;
            font-weight: normal;
            vertical-align: middle;
        }

        .unread td {
            font-weight: bold;
        }

        .read td {
            font-weight: normal;
        }

        tr:hover {
            background-color: #f1f1f1;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="inbox-container">
        <table id="inbox-table">
            <tbody id="inbox-body">
            </tbody>
        </table>
    </div>

    <script type="text/javascript">
        console.log("Outside");
        (function() {
            const inboxData = [
                { sender: "Google", subject: "Welcome to Gmail!", date: "Oct 5, 2024", isRead: false },
                { sender: "GitHub", subject: "New Pull Request", date: "Oct 4, 2024", isRead: true },
                { sender: "Amazon", subject: "Your Order has Shipped", date: "Oct 3, 2024", isRead: false },
                { sender: "Netflix", subject: "New Movies this Week", date: "Oct 2, 2024", isRead: true },
                { sender: "Spotify", subject: "Your Playlist Summary", date: "Oct 1, 2024", isRead: false },
                { sender: "LinkedIn", subject: "New Connection Request", date: "Sep 30, 2024", isRead: true }
                
            ];

            function loadInbox() {
                console.log("Inside");
                const inboxTableBody = document.getElementById('inbox-body');
                inboxTableBody.innerHTML = ''; // Clear any existing content
                inboxData.forEach((email, index) => {
                    const row = document.createElement('tr');
                    row.classList.add(email.isRead ? 'read' : 'unread');

                    const senderCell = document.createElement('td');
                    senderCell.textContent = email.sender;

                    const subjectCell = document.createElement('td');
                    subjectCell.textContent = email.subject;

                    const dateCell = document.createElement('td');
                    dateCell.textContent = email.date;

                    row.appendChild(senderCell);
                    row.appendChild(subjectCell);
                    row.appendChild(dateCell);

                    row.addEventListener('click', () => markAsRead(index, row));
                    inboxTableBody.appendChild(row);
                });
            }

            function markAsRead(index, row) {
                inboxData[index].isRead = true; 
                row.classList.remove('unread');
                row.classList.add('read'); 
                console.log('Email marked as read');
            }

                loadInbox();
        })();
    </script>
</body>
</html>
