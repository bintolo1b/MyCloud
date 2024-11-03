const rows = document.querySelectorAll('#out-table tbody tr');
console.log(rows);

rows.forEach(row => {
    row.addEventListener("click", function() {
		console.log("wtf")
        const urlElement = row.querySelector(".tr_url");
        if (urlElement) {
            const url = urlElement.textContent.trim();
            if (url) {
                window.location.href = url;
            }
        }
    });
});
