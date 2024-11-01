const rows = document.querySelectorAll('#inbox-table tbody tr');

rows.forEach(row => {
    row.addEventListener("click", function() {
        const urlElement = row.querySelector(".tr_url");
        console.log(urlElement.value)
        if (urlElement) {
            const url = urlElement.textContent.trim();
            if (url) {
                window.location.href = url;
                
            }
        }
    });
});
