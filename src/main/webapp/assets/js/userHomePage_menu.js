const currentUrl = window.location.pathname;

const navLinks = document.querySelectorAll('.userHomePageItem a');

const newButton = document.querySelector('a.waves-effect.waves-light.btn.btn-flat.white-text');
const Search = document.querySelector('input[type="search"]')


navLinks.forEach(link => {
    if (link.getAttribute('href') === currentUrl) {
		if (currentUrl !== '/PBL4/userhomepage/main') {
			Search.disabled = true;
			document.querySelectorAll('.material-icons').forEach(element => {
		    element.onmouseover = () => element.style.cursor = 'default'; // or any desired cursor style
		    element.onmouseout = () => element.style.cursor = '';
			});
			newButton.className = '';
		}
        const icon = link.querySelector('i');
        if (icon) {
            icon.classList.add('blue-text', 'text-darken-1');
        }

        link.parentElement.classList.add('active');
    }
});

