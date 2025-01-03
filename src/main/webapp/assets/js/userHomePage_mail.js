const rows = document.querySelectorAll('#inbox-table tbody tr');
const newBtn = document.querySelector('a.waves-effect.waves-light.btn.btn-flat.white-text');

rows.forEach(row => {
    row.addEventListener("click", function() {
        const urlElement = row.querySelector(".tr_url");
        if (urlElement) {
            const url = urlElement.textContent.trim();
            if (url) {
                window.location.href = url;
                
            }
        }
    });
});
