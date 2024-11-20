

function updateAvatar(event) {
    const avatar = document.getElementById('avatar');
    const file = event.target.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            avatar.src = e.target.result;
        };
        reader.readAsDataURL(file);
    }

    const formData = new FormData();
    formData.append('avatar', file);

    fetch('/PBL4/updateAvatar', {
            method: 'POST',
            body: formData
        })
        .then(function(response){
            if (!response.ok) {
                    throw new Error('Network response was not ok');
            }
            else
                return response.json();
        })
        .then(function(returnObject){
            alert(returnObject.message);
            if (returnObject.message === 'Update successfully!'){
                location.reload();
            }
        })
        .catch(function(error){
            console.log(error);
        })

}
        
function updateFullName() {
	document.querySelector('.update-fullname').style.display = 'block';
	document.querySelector('.save-fullname-button').style.display = 'block';
	document.querySelector('.update-password').style.display = 'none';
	document.querySelector('.save-new-password-button').style.display = 'none';
}

function changePassword() {
	document.querySelector('.update-password').style.display = 'block';
	document.querySelector('.save-new-password-button').style.display = 'block';
	document.querySelector('.update-fullname').style.display = 'none';
	document.querySelector('.save-fullname-button').style.display = 'none';
}

document.getElementById('update-name-form').onsubmit = function(event) {
    event.preventDefault();

    var data = {
        newFullName: document.getElementById('fullName-Text').value
    }
    
    fetch("/PBL4/updateFullName",{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
		})
		.then(function(response){
			if (!response.ok) {
           	 	throw new Error('Network response was not ok');
       		}
			else
				return response.json();
		})
		.then(function(returnObject){
            alert(returnObject.message);
			if (returnObject.message === 'Update successfully!'){
                location.reload();
			}
		})
		.catch(function(error){
			console.log(error);
		})
}

document.getElementById('update-password-form').onsubmit = function(event) {
    event.preventDefault();

    var data = {
        currentPassword: document.getElementById('currentPassword').value,
        newPassword: document.getElementById('newPassword').value,
        verifyNewPassword: document.getElementById('verifyNewPassword').value
    }
    
    fetch("/PBL4/updatePassword",{
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
		})
		.then(function(response){
			if (!response.ok) {
           	 	throw new Error('Network response was not ok');
       		}
			else
				return response.json();
		})
		.then(function(returnObject){
            alert(returnObject.message);
			if (returnObject.message === 'Update successfully!'){
                location.reload();
			}
		})
		.catch(function(error){
			console.log(error);
		})
}