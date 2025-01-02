async function showPasswordInputs() {
    const usernameInput = document.getElementById('username').value;
    if (usernameInput !== "") {
        try{
            const response = await fetch('/PBL4/getnamebyusername?username=' + usernameInput);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const returnObject = await response.json();
        
            if (returnObject.message !== "Username not found!"){
                document.getElementById('user-fullname').value = returnObject.message;
                document.getElementById('password-inputs').classList.remove('hidden');
            }
            else{
                alert(returnObject.message);
                document.getElementById('user-fullname').value = "";
                document.getElementById('password-inputs').classList.add('hidden');
            }
        }
        catch(error){
            console.error(error);
        }  
    }  
}

document.querySelector('.update-password-form').addEventListener('submit',  function(event) {
    event.preventDefault();
    const username = document.getElementById('username').value;
    const newPassword = document.getElementById('new-password').value;
    const verifyNewPassword = document.getElementById('confirm-password').value;

    const data = {
        username: username,
        newPassword: newPassword,
        verifyNewPassword: verifyNewPassword
    }

    if (newPassword === verifyNewPassword){
        fetch("/PBL4/admin/updateAllUserPassword",{
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
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
            })
            .catch(function(error){
                console.log(error);
            })
    }
    else{
        alert("Passwords do not match!");
    }
});