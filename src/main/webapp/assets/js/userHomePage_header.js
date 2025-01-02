const searchInput = document.querySelector('input[type="search"]');
const suggestionsList = document.getElementById('suggestionsList');

// Show suggestions list when input is focused
searchInput.addEventListener('focus', () => {
    suggestionsList.style.display = 'block';
});

// Hide suggestions list when input loses focus, with a slight delay to allow item click
searchInput.addEventListener('blur', () => {
        suggestionsList.style.display = 'none';
});

document.querySelector('input[type="search"]').addEventListener('input', function() {
    var searchContent = this.value;

    if (searchContent.length > 0){
        var searchContentEncoded = encodeURIComponent(searchContent);
        var searchUrl = '/PBL4/search?searchContent=' + searchContentEncoded;

        const suggestionlist = document.getElementById('suggestionsList');

        fetch(searchUrl)
            .then(function(response) {
                if (!response.ok) {
           	    	throw new Error('Network response was not ok');
                }
                else
                    return response.json();
            })
            .then(function(data) {
                suggestionlist.innerHTML = '';
                data.slice(0, 5).forEach(function(item) {
                    var srcUrl;
                    if (item.Type === 'docx')
                        srcUrl = '/PBL4/assets/img/wordImage.png';
                    else if (item.Type === 'pdf')
                        srcUrl = '/PBL4/assets/img/pdfImage.png';
                    else if (item.Type === '.jpg' || item.Type === '.jpeg' || item.Type === '.png')
                        srcUrl = '/PBL4/assets/img/jpegImage.png';
                    else if (item.Type === 'folder')
                        srcUrl = '/PBL4/assets/img/folderImage.png';
                    else
                        srcUrl = '/PBL4/assets/img/file.png';
                    
                    const li = document.createElement('li');
                    li.innerHTML = `
                        <img alt="" src="${srcUrl}" class="suggestFileImg">
                        <div class="suggestInfor">
                            <div class="suggestInfor_first">
                                <span class="suggestFileName">${item.Name}</span>
                            </div>
                            <div class="SuggestInfor_second">							
                                <span class="suggestFileDate">${item.UploadDate}</span>   
                            </div>
                        </div>
                    `;
                    suggestionlist.appendChild(li);
                    
                    if (item.Type === 'folder')
                        li.addEventListener('mousedown', function() {
                            const fileName = li.querySelector('.suggestFileName').textContent;
                            searchInput.value = fileName;
                            suggestionsList.style.display = 'none';
                            const folderPathEncoded = encodeURIComponent(item.Path);
                            window.location.href = `/PBL4/userhomepage/main?folderPath=${folderPathEncoded}`;
                        });
                    else
                        li.addEventListener('mousedown', function() {
                            const fileName = li.querySelector('.suggestFileName').textContent;
                            searchInput.value = fileName;
                            suggestionsList.style.display = 'none';
                            const folderPathEncoded = encodeURIComponent(item.folderPath);
                            const fileNameEncoded = encodeURIComponent(item.Name);
                            window.open(`/PBL4/displayfilecontroller?folderPath=${folderPathEncoded}&fileName=${fileNameEncoded}`, '_blank');
                        });
                });
            })
            .catch(function(error) {
                console.log(error);
            });

    }
});

document.querySelectorAll('.notify-item').forEach(function(item) {
    item.addEventListener('click', async function() {
        var url = this.attributes['data-url'].value;
        var id = this.attributes['data-id'].value;
        const response = await fetch('/PBL4/checkreadnotification', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ notificationId: id})
        });
        window.location.href = url;
    }); 
});

const notifyList = document.getElementById('notifyList');
if (notifyList && notifyList.querySelector('.unread')) {
    document.querySelector('.new-notify').style.display = 'block';
}

const notificationWS = new WebSocket(`ws://${window.location.host}/PBL4/notification`);

function sendMailNotification(receiverUsername, content, sentUsername, accessLink) {
    const notification = {
        receiverUsername : receiverUsername,
        content : content,
        sentUsername : sentUsername,
        accessLink : accessLink
    };
    notificationWS.send(JSON.stringify(notification));
}

notificationWS.onmessage = function(event) {
    const notification = JSON.parse(event.data);
    const notificationItem = document.createElement('li');
    notificationItem.className = 'notify-item unread';

    notificationItem.onclick = async function() { 
        const response = await fetch('/PBL4/checkreadnotification', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ notificationId: notification.id })
        });
        window.location.href = notification.accessLink;
    }
    notificationItem.innerHTML = `    
                                <img alt="" src="/PBL4/avatar/${notification.sentUsername}.jpg" class="notifyAvt">
                                <div class="notifyInfor">
                                    <div class="notifyInfor_first">
                                        <span class="notifyContent">${notification.content}</span>
                                    </div>
                                    <div class="notifyInfor_second">
                                        <span class="notifyDate">${notification.time}</span>
                                    </div>
                                </div>
    `;

    if (notifyList.firstChild) {
        notifyList.insertBefore(notificationItem, notifyList.firstChild);
    } else {
        notifyList.appendChild(notificationItem);
    }

    document.querySelector('.new-notify').style.display = 'block';
}

