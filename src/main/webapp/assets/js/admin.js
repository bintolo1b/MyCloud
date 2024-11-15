 function showSection(sectionId, element) {
        // Hide all sections
        document.querySelectorAll('.container').forEach(container => container.classList.add('hidden'));

        // Show the selected section
        document.getElementById(sectionId).classList.remove('hidden');

        // Update active link
        document.querySelectorAll('.sidenav a').forEach(link => link.classList.remove('active'));
        element.classList.add('active');
    }

    function showPasswordInputs() {
        const usernameInput = document.getElementById('username').value;
        if (usernameInput.trim() !== '') {
            document.getElementById('password-inputs').classList.remove('hidden');
        }
    }

    function showUserDetails() {
        const usernameInput = document.getElementById('storage-username').value;
        if (usernameInput.trim() !== '') {
            document.getElementById('storage-inputs').classList.remove('hidden');
            document.getElementById('user-fullname').value = 'NguyenVanX';
            document.getElementById('storage-capacity').value = '0.5 GB / 1 GB';
        }
    }