 function showSection(sectionId, element) {
        // Update active link
        document.querySelectorAll('.sidenav a').forEach(link => link.classList.remove('active'));
        element.classList.add('active');
    }