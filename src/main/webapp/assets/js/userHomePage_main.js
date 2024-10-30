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


 document.getElementById('attachment').addEventListener('change', function(event) {
   		event.preventDefault(); // Ngăn chặn reload khi chọn file
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
    
    const renameModal = document.getElementById('renameModal');
    
    document.querySelectorAll('.rename-btn').forEach(button => {
	    button.addEventListener('click', function(event) {
	        event.preventDefault(); // Ngăn chặn hành động mặc định của liên kết
	        const fileName = this.getAttribute('data-file-name'); // Lấy tên file từ data-file-name
	        //alert(fileName);
	        document.getElementById('renameInput').value = fileName; // Đặt tên file vào input của modal
	        document.getElementById('renameModal').classList.add('open'); // Thêm class .open để hiển thị modal
            document.getElementById('oldName').value = fileName;

            if (this.closest('.folder-container')) {
                document.getElementById('isFolder').value = 'true';
            } else if (this.closest('.file-container')) {
                document.getElementById('isFolder').value = 'false';
            } else {
                document.getElementById('isFolder').value = 'undefined';
            }
	    });
	});
	
	document.getElementById('cancel-rename-modal-btn').addEventListener('click', function() {
	    document.getElementById('renameModal').classList.remove('open'); // Xóa class .open để ẩn modal
	});
	
	window.onclick = function(e) {
		if(e.target == renameModal) {
			renameModal.classList.remove('open');
		}
	}

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

const newFolderModal = document.getElementById("newFolderModal");
const cancelNewFolderModalBtn = document.getElementById("cancel-new-folder-modal-btn");
const createNewFolderBtn = document.getElementById("create-new-folder-modal-btn");

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
        
        if (this.id === 'create-new-folder') {
            newFolderModal.classList.add('open');
            hideModal();
            return; // Stop further execution for this item
        }

        if (inputFile) {
            inputFile.click();
        }
    });
});

    document.addEventListener('DOMContentLoaded', function() {
        const fileInputs = document.querySelectorAll('.uploadItem');

        fileInputs.forEach(input => {
            input.addEventListener('change', function() {
                if (this.files.length > 0) {
                    const form = this.closest('form');
                    form.submit();
                }
            });
        });
    });
    
cancelNewFolderModalBtn.onclick = function() {
        newFolderModal.classList.remove('open');
        console.log(newFolderModal);
};

window.onclick = function(e) {
    newFolderModal.classList.remove('open');
    if(e.target == newFolderModal) {
        newFolderModal.classList.remove('open');
    }
}

document.getElementById('sendMailForm').addEventListener('submit', function(event){
    event.preventDefault();
    
    var fd = new FormData();
    fd.append("receiverUsername", document.getElementById('to').value);
    fd.append("topic", document.getElementById('subject').value);
    fd.append("content", document.getElementById('message').value);
    
    var files = document.getElementById('attachment').files;
    for (var i = 0; i < files.length; i++) {
        fd.append("attachment", files[i]); // Sử dụng "attachments[]" để gửi như một mảng
    }
    
    fetch('http://localhost:8080/PBL4/sendmail',{
        method: 'POST',
        body: fd
        })
        .then(function(response){
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            else
                return response.json();
        })
        .then(function(returnObject){
            console.log(returnObject.message);
            if (returnObject.message === 'Sent Successfully!'){
                document.getElementById("composeModal").style.display = 'none';
                alert("Sent successfully!");
            }
            else{
                alert(returnObject.message);
            }		
        })
        .catch(function(error){
            console.log(error);
        })
})

document.getElementById('newFolderForm').addEventListener('submit', function(event){
    event.preventDefault();

    var formData = {
        folderName : document.getElementById('folderName').value,
        folderPath : document.getElementById('folderPath_createFolder').value
    }

    fetch('http://localhost:8080/PBL4/createnewfolder',{
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(formData)
		})
		.then(function(response){
			if (!response.ok) {
           	 	throw new Error('Network response was not ok');
       		}
			else
				return response.json();
		})
		.then(function(returnObject){
            alert(returnObject.message);
			if (returnObject.message === 'Created sucessfully!'){
                var folderPath = document.getElementById('folderPath_createFolder').value;
                var encodedFolderPath = encodeURIComponent(folderPath);
                window.location.href = `http://localhost:8080/PBL4/userhomepage/main?folderPath=${folderPath}`;
			}
		})
		.catch(function(error){
			console.log(error);
		})
})

document.getElementById('renameForm').addEventListener('submit', function(event){
    event.preventDefault();

    var formData = {
        folderPath : document.getElementById('folderPath_rename').value,
        oldName : document.getElementById('oldName').value,
        newName : document.getElementById('renameInput').value
    }

    var isFolder = document.getElementById('isFolder').value;
    var url = '';
    if (isFolder === 'true'){
        url = 'http://localhost:8080/PBL4/renamefolder';
    }
    else if (isFolder === 'false'){
        url = 'http://localhost:8080/PBL4/renamefile';
    }

    console.log(url)

    fetch(url,{
		method: 'POST',
		headers: {
			'Content-Type': 'application/json'
		},
		body: JSON.stringify(formData)
		})
		.then(function(response){
			if (!response.ok) {
           	 	throw new Error('Network response was not ok');
       		}
			else
				return response.json();
		})
		.then(function(returnObject){
            alert(returnObject.message);
			if (returnObject.message === 'Rename successfully!'){
                var folderPath = document.getElementById('folderPath_rename').value;
                var encodedFolderPath = encodeURIComponent(folderPath);
                window.location.href = `http://localhost:8080/PBL4/userhomepage/main?folderPath=${encodedFolderPath}`;
			}
		})
		.catch(function(error){
			console.log(error);
		})
})
