
document.getElementById('loginForm').addEventListener('submit', function(event){
	event.preventDefault();
	
	var formData = {
		username: document.getElementById('username').value,
		password: document.getElementById('password').value
	}
	
	fetch('http://localhost:8080/PBL4/login',{
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
			if (returnObject.message === 'Login successfully'){
				window.location.href = `http://localhost:8080/PBL4/userhomepage`;
			}
			else{
				document.getElementById('password').value = '';
			}
			
		})
		.catch(function(error){
			console.log(error);
		})
});