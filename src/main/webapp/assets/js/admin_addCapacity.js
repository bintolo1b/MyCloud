function showUserDetails() {
        const usernameInput = document.getElementById('storage-username').value;
        if (usernameInput.trim() !== '') {
            document.getElementById('storage-inputs').classList.remove('hidden');
            document.getElementById('user-fullname').value = 'NguyenVanX';
            document.getElementById('storage-capacity').value = '0.5 GB / 1 GB';
        }
    }