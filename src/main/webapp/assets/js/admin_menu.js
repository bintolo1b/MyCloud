 function showSection(sectionId, element) {
        // Update active link
        document.querySelectorAll('.sidenav a').forEach(link => link.classList.remove('active'));
        element.classList.add('active');
}

document.querySelectorAll('.admin_item').forEach(function(item) {
    var url = item.getAttribute('href');
    if (url === window.location.pathname) {
        item.classList.add('active');
    }
});
