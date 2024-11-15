 function showPasswordInputs() {
        const usernameInput = document.getElementById('username').value;
        if (usernameInput.trim() !== '') {
			document.getElementById('user-fullname').value = 'NguyenVanX';
            document.getElementById('password-inputs').classList.remove('hidden');
        }
    }