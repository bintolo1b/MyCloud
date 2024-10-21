const menuItems = document.querySelectorAll('.userHomePageItem');

menuItems.forEach(item => {
    item.addEventListener('click', function () {
        menuItems.forEach(i => {
            i.classList.remove('active');
            const icon = i.querySelector('i.material-icons');
            if (icon) {
                icon.classList.remove('blue-text', 'text-darken-1');
            }
        });

        this.classList.add('active');

        const clickedIcon = this.querySelector('i.material-icons');
        if (clickedIcon) {
            clickedIcon.classList.add('blue-text', 'text-darken-1');
        }
    });
});