
document.addEventListener('DOMContentLoaded', function() { 
    const initialMainContent = document.querySelector('.main').innerHTML;
    let currentIntervalIds = []; // Mảng để lưu trữ các intervalId hiện tại

    initializeMainContent();

    document.querySelectorAll('.userHomePageItem').forEach(item => {
        item.addEventListener('click', function(event) {
            //event.preventDefault();

            // Remove 'active', 'blue-text', 'text-darken-1' from all items and icons
            document.querySelectorAll('.userHomePageItem').forEach(i => {
                i.classList.remove('active');
                const icon = i.querySelector('i.material-icons');
                if (icon) {
                    icon.classList.remove('blue-text', 'text-darken-1');
                }
            });

            // Add 'active' to the clicked item
            this.classList.add('active');

            // Add 'blue-text', 'text-darken-1' to the icon inside the clicked item
            const itemIcon = this.querySelector('i.material-icons');
            if (itemIcon) {
                itemIcon.classList.add('blue-text', 'text-darken-1');
            }
        });
    });

    function clearAllIntervals() {
        currentIntervalIds.forEach(intervalId => {
            clearInterval(intervalId);
        });
        currentIntervalIds = []; // Reset lại mảng
    }

    // Override native setInterval để lưu trữ các intervalId
    const originalSetInterval = window.setInterval;
    window.setInterval = function(fn, delay) {
        const intervalId = originalSetInterval(fn, delay);
        currentIntervalIds.push(intervalId);
        return intervalId;
    };
});



// Initialize main content behavior, called after loading new content
function initializeMainContent() {
	const composeBtn = document.getElementById("composeBtn");
    const closeComposeModal = document.getElementById("closeComposeModal");
    const composeModal = document.getElementById("composeModal");

    if (composeBtn) {
        composeBtn.addEventListener("click", function() {
            composeModal.style.display = "flex";
        });
    }

    if (closeComposeModal) {
        closeComposeModal.addEventListener("click", function() {
            composeModal.style.display = "none";
        });
    }

    // Đóng modal khi click bên ngoài nội dung modal
    window.onclick = function(event) {
        if (event.target == composeModal) {
            composeModal.style.display = "none";
        }
    };
	
    // Kebab menu click behavior
    document.querySelectorAll('.kebab-container').forEach(container => {
        container.addEventListener('click', function(e) {
            e.stopPropagation(); 
            const menu = this.querySelector('.kebab-items-list');
            const isOpen = menu.style.display === 'block';

            // Close all open kebab menus
            document.querySelectorAll('.kebab-items-list').forEach(m => {
                m.style.display = 'none';
            });

            // Toggle the clicked menu
            menu.style.display = isOpen ? 'none' : 'block';
        });
    });

    // Close kebab menu when clicking outside
    document.addEventListener('click', function(event) {
        document.querySelectorAll('.kebab-items-list').forEach(menu => {
            if (!menu.parentElement.contains(event.target)) {
                menu.style.display = 'none';
            }
        });
    });

    // Double-click file card to view or open in a modal/new tab
   document.querySelectorAll('.card-panel.file').forEach(card => {
    card.addEventListener('dblclick', function() {
        const url = this.getAttribute('data-url');
        const displayModal = document.querySelector('.display-modal');
        const iframe = document.querySelector('.display-modal iframe');

        // Handle image files (jpg, png, gif)
        if (url.match(/\.(jpeg|jpg|gif|png)$/i)) {
            const img = new Image();
            img.src = url;

            img.onload = function() {
                // Clear previous content
                displayModal.innerHTML = ''; // Clear any existing content in the modal
                displayModal.appendChild(img); // Append the image

                // Adjust image size according to viewport
                if (img.naturalWidth >= window.innerWidth * 0.9 || img.naturalHeight >= window.innerHeight * 0.9) {
                    img.style.maxWidth = '90%'; // Scale down to 90% of viewport
                    img.style.maxHeight = '90%'; // Scale down to 90% of viewport
                } else {
                    img.style.width = `${img.naturalWidth}px`; // Set original width
                    img.style.height = `${img.naturalHeight}px`; // Set original height
                }

                // Show modal
                displayModal.style.display = 'flex';
                displayModal.classList.add('open');
            };
        } else {
            // Open non-image files in a new tab
            window.open(url, '_blank');
        }

        // Close modal when clicking outside the content
        displayModal.addEventListener('click', function(e) {
            if (e.target === displayModal) {
                displayModal.classList.remove('open');
                displayModal.style.display = 'none';
                iframe.src = ''; // Reset iframe content
                displayModal.innerHTML = ''; // Clear modal content when closed
            }
        });
    });
});
    
    document.querySelectorAll('.card-panel.file').forEach((card, index) => {
    	
        const folderPath = "${folderPath.replace('\\', '\\\\')}";
        console.log(folderPath);
        const fileName = card.querySelector('.file-details span').innerText;
        let imgId = card.querySelector('.preview-panel img').id;
        assignPDFImgToImgTag(folderPath, fileName, imgId);
    });
}
		document.addEventListener('click', function(event) {
		    const notifyIcon = document.querySelector('.notify');
		    const notifyBlock = document.querySelector('.notify-block');
		
		    if (!notifyIcon.contains(event.target) && !notifyBlock.contains(event.target)) {
		        notifyBlock.classList.remove('open'); 
		    }
		});
		
		document.querySelector('.notify').addEventListener('click', function(e) {
		    const notifyBlock = document.querySelector('.notify-block'); 
		
			notifyBlock.classList.toggle('open');
			
		    e.stopPropagation();
		});


      

        const newBtn = document.querySelector('a.waves-effect.waves-light.btn.btn-flat.white-text');
        const modal = document.querySelector('.js-modal');
        const modalClose = document.querySelector('.js-modal-close');
        const modalContainer = document.querySelector('.js-modal-container');

        function showModal() {
            modal.classList.add('open');
        }

        function hideModal() {
            modal.classList.remove('open');
        }

        newBtn.addEventListener('click', showModal);

        modalClose.addEventListener('click', hideModal);

        modal.addEventListener('click', hideModal);

        modalContainer.addEventListener('click', function(event) {
            event.stopPropagation();
        });
        
        
        document.querySelectorAll('.new-item').forEach(item => {
            item.addEventListener('click', function(e) {
                const inputFile = this.querySelector('input[type="file"]');

                if (e.target.tagName.toLowerCase() === 'a' || e.target.tagName.toLowerCase() === 'i') {
                    e.preventDefault(); 
                }

                if (inputFile) {
                    inputFile.click();
                }
            });
        });

        document.addEventListener('DOMContentLoaded', function() {
            const fileInputs = document.querySelectorAll('input[type="file"]');

            fileInputs.forEach(input => {
                input.addEventListener('change', function() {
                    if (this.files.length > 0) {
                        const form = this.closest('form');
                        form.submit();
                    }
                });
            });
        });