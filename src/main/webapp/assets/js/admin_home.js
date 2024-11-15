function updateUserStatus() {
    document.querySelectorAll('.status').forEach(userStatus => {
        var username = userStatus.getAttribute('data-username');
        var url = `/PBL4/admin/checkifuseronline?username=${username}`;
        fetch(url)
            .then(function (response) {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                else
                    return response.json();
            })
            .then(function (returnObject) {
                if (returnObject.message === 'Online') {
                    userStatus.innerHTML = 'Online';
                    userStatus.classList.remove('Offline');
                    userStatus.classList.add('Online');
                }
                else if (returnObject.message === 'Offline') {
                    userStatus.innerHTML = 'Offline';
                    userStatus.classList.remove('Online');
                    userStatus.classList.add('Offline');
                }

            })
            .catch(function (error) {
                console.log(error);
            })
    });
    
}

setTimeout(() => {
    setInterval(updateUserStatus, 2000);
}, 2000);
