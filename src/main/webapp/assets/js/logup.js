document.getElementById('form').addEventListener('submit', function(event){
	event.preventDefault();
	
	var formData = {
		username: document.getElementById('username').value,
		password: document.getElementById('password').value,
		fullName: document.getElementById('fullName').value,
		verifyPassword: document.getElementById('verifyPassword').value
	}
	
	fetch('/PBL4/checkLogup',{
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(formData)
		})
		.then(function(response){
			if (!response.ok) {
           	 	throw new Error('Network response was not ok');
       		}
			else
				return response.json();
		})
		.then(function(returnObject){
			if (returnObject.message === "Logup successfully!") {
	            showSuccessToast(returnObject.message);
	            window.location.href = `userhomepage/main`;
	        } else {
	            if (returnObject.message === "Username exists!") {
	                document.getElementById('username').value = '';
	            } else if (returnObject.message === "Verify password is incorrect!") {
	                document.getElementById('password').value = '';
	                document.getElementById('verifyPassword').value = '';
	            }
	            console.log(returnObject.message);
	            showErrorToast(returnObject.message);  
	            }
			})
		.catch(function(error){
			console.log(error);
		})
});

function showErrorToast(message){
    toast({
       title: 'Error',
       message: message,
       type: 'error',
       duration: 3000
    });
}


function showSuccessToast(message) {
    toast({
        title: 'Success',
        message: message,  // Display the message passed from the server
        type: 'success',
        duration: 3000
    });
}

 function toast({title = '',message = '',type = 'info',duration = 3000}) {
    const main = document.getElementById('toast');
    if(main) {
        const toast = document.createElement('div');
        const autoRemoveID = setTimeout(function() {
            main.removeChild(toast);
        }, duration + 1000);
        toast.onclick = function(e) {
            if(e.target.closest('.toast__close')){
                main.removeChild(toast);
                clearTimeout(autoRemoveID);
            }
        }

        const icons = {
            success: 'fas fa-check-circle',
            info: 'fas fa-info-circle',
            warning: 'fas fa-exclamation-circle',
            error: 'fas fa-exclamation-circle'
        };
        const icon = icons[type];
        const delay = (duration/1000).toFixed(2);

        toast.classList.add('toast', `toast--${type}`);
        toast.style.animation = `slideInLeft ease .3s, fadeOut linear 1s ${delay}s forwards`;

        toast.innerHTML = `
        <div class="toast__icon">
        <i class="${icon}"></i>
        </div>
        <div class="toast__body">
        <h3 class="toast__title">${title}</h3>
        <p class="toast__msg">${message}</p>
        </div>
        <div class="toast__close">
        <i class="fa-solid fa-xmark"></i>
        </div>
        `;
        main.appendChild(toast);
    }
 }