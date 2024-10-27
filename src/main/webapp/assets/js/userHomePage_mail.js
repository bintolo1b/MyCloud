const inboxRows = document.querySelectorAll('#inbox-table tbody tr');
const emailDetails = document.getElementById('email-details');
const backButton = document.getElementById('back-button');

const emailSender = document.getElementById('email-sender');
const emailSubject = document.getElementById('email-subject');
const emailContent = document.getElementById('email-content');
const emailAttachment = document.getElementById('email-attachment');
const emailDatetime = document.getElementById('email-date');

inboxRows.forEach(row => {
    row.addEventListener('click', () => {
		 if (row.classList.contains('unread')) {
            row.classList.remove('unread');
            row.classList.add('read');
        }

		
        const sender = row.children[0].textContent;
        const subject = row.children[1].textContent;
        //const content = row.children[2].textContent;
        //const attachment = row.children[3].textContent;
        const datetime = row.children[2].textContent;

        emailSender.textContent = `From: ${sender}`;
        emailSubject.textContent = `Subject: ${subject}`;
        //emailContent.textContent = `Content: ${content}`;
        //emailAttachment.textContent = `Attachment: ${attachment}`
        emailDatetime.textContent = `Datetime: ${datetime}`;

        document.querySelector('.inbox-container').style.display = 'none';
        emailDetails.classList.add('visible');
    });
});

backButton.addEventListener('click', () => {
    emailDetails.classList.remove('visible');
    document.querySelector('.inbox-container').style.display = 'block';
});