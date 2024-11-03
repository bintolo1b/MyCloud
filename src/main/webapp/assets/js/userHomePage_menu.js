const currentUrl = window.location.pathname;

const navLinks = document.querySelectorAll('.userHomePageItem a');

navLinks.forEach(link => {
    if (link.getAttribute('href') === currentUrl) {
        const icon = link.querySelector('i');
        if (icon) {
            icon.classList.add('blue-text', 'text-darken-1');
        }

        link.parentElement.classList.add('active');
    }
});