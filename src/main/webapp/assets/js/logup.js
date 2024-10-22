document.getElementById('form').addEventListener('submit', function(event){
	event.preventDefault();
	
	var formData = {
		username: document.getElementById('username').value,
		password: document.getElementById('password').value,
		fullName: document.getElementById('fullName').value,
		verifyPassword: document.getElementById('verifyPassword').value
	}
	
	fetch('http://localhost:8080/PBL4/logup',{
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
			console.log(returnObject.message);
			//message : Logup successfully!, Lack of information!, Username exists!, Verify password is incorrect!
			//dang ki xong thi thong bao da dang ki thanh cong, xong roi chuyen huong toi /userhomepage/main 
			
		})
		.catch(function(error){
			console.log(error);
		})
});