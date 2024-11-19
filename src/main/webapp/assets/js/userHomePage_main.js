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
    
    const inputTo = document.getElementById('to');
	const receiverName = document.querySelector('.receiverName');
    	
	const attachFile = document.querySelector('.attach-file');
	const attachBtn = document.querySelector('.attach-btn');
	
	function updateReceiverNameVisibility() {
	    if (composeModal.style.display === 'none') {
	        receiverName.style.display = 'none'; // Ẩn receiverName nếu composeModal không hiển thị
	    } else {
	        receiverName.style.removeProperty('display'); // Hiển thị receiverName nếu composeModal hiển thị
	    }
	}
   
    if (composeBtn) {
        composeBtn.addEventListener("click", function() {
            composeModal.style.display = "flex";
        });
    }

    if (closeComposeModal) {
        closeComposeModal.addEventListener("click", function() {
            composeModal.style.display = "none";
                updateReceiverNameVisibility();
        });
    }

    // Đóng modal khi click bên ngoài nội dung modal
    window.onclick = function(event) {
        if (event.target == composeModal) {
            composeModal.style.display = "none";
                updateReceiverNameVisibility();
        }
    };
		
		    // Thêm sự kiện blur cho input
			inputTo.addEventListener('blur', async function() {
			    // Hiển thị span khi input mất focus
                var receiverUsername = document.getElementById('to').value;
                try{
                    const response = await fetch('/PBL4/getnamebyusername?username=' + receiverUsername);
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }

                    const returnObject = await response.json();
                    if (returnObject != null) {
                        receiverName.textContent = returnObject.message;
                        const sendBtn = document.getElementsByClassName('send-btn')[0];
                        if (returnObject.message !== "Username not found!"){
                            sendBtn.disabled = false;
                            sendBtn.style.backgroundColor = '#28a745';
                            receiverName.style.color = '#1155cc';
                        }
                        else{
                            sendBtn.disabled = true;
                            sendBtn.style.backgroundColor = '#6c757d';
                            receiverName.style.color = 'red';
                        }
                    }
                    receiverName.style.display = 'block';

                    }
                catch (error){
                    console.error(error);
                }
			});
			
			// Lấy phần tử input file và container chứa tên file đính kèm
			const attachmentInput = document.getElementById("attachment");
			const attachedFilesContainer = document.getElementById("attachedFilesContainer");
			
			let selectedFiles = []; // Danh sách file đã chọn
			
			attachmentInput.addEventListener("change", function() {
			    // Cập nhật danh sách file đã chọn
			    selectedFiles = Array.from(attachmentInput.files); // Lưu trữ file đã chọn
			    updateAttachedFiles(); // Gọi hàm để hiển thị các file
			});
			
			function updateAttachedFiles() {
			    attachedFilesContainer.innerHTML = ""; // Xóa nội dung cũ
			
			    if (selectedFiles.length > 0) {
			        attachedFilesContainer.style.display = "flex"; // Hiện container
			        attachedFilesContainer.style.flexDirection = "column"; // Sắp xếp theo chiều dọc
			
			        selectedFiles.forEach((file, index) => {
			            // Tạo một thẻ div cho mỗi file
			            const fileContainer = document.createElement("div");
			            fileContainer.className = "attach-file"; // Áp dụng class để tạo kiểu
			
			            const fileNameSpan = document.createElement("span");
			            fileNameSpan.textContent = file.name;
			            fileNameSpan.className = "attach-file-name";
			
			            const closeButton = document.createElement("span");
			            closeButton.className = "close-attach-file";
			            closeButton.textContent = "×";
			
			            // Thêm sự kiện cho nút đóng
			            closeButton.addEventListener("click", () => {
			                // Xóa file khỏi danh sách đã chọn
			                selectedFiles.splice(index, 1); // Xóa file theo chỉ số
			                updateAttachedFiles(); // Cập nhật hiển thị
			                updateInputFiles(); // Cập nhật lại input file
			            });
			
			            // Gắn các phần tử vào fileContainer
			            fileContainer.appendChild(fileNameSpan);
			            fileContainer.appendChild(closeButton);
			
			            // Thêm fileContainer vào attachedFilesContainer
			            attachedFilesContainer.appendChild(fileContainer);
			        });
			    } else {
			        attachedFilesContainer.style.display = "none"; // Ẩn container nếu không còn file nào
			    }
			}

function updateInputFiles() {
    // Tạo một DataTransfer object để cập nhật danh sách file của input
    const dataTransfer = new DataTransfer();
    selectedFiles.forEach(file => dataTransfer.items.add(file)); // Thêm các file còn lại

    attachmentInput.files = dataTransfer.files; // Cập nhật input file
}

	
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
	
		const renameInput = document.getElementById("renameInput");
		const renameNotice = document.querySelector(".rename-notice span");
		const oldNameInput = document.getElementById("oldName");
		const isFolderInput = document.getElementById("isFolder");
		const confirmRenameButton = document.querySelector(".confirm-rename-modal-btn");
		
		const fileNamePattern = /^[\p{L}a-zA-Z0-9\s_()\/-]+(\.[a-zA-Z0-9]{2,4})$/u;
		const folderNamePattern = /^[\p{L}a-zA-Z0-9\s_()\/-]+$/u;
		const invalidCharsPattern = /[\\/:*?"<>|]/; // Kiểm tra ký tự không hợp lệ
		
		renameInput.addEventListener("blur", function () {
		    renameNotice.parentElement.classList.remove("visible");
		});
		
		// Khi mở modal, kiểm tra xem oldName có phải là file hay folder
		const oldName = oldNameInput.value;
		if (oldName.includes('.')) {
		    isFolderInput.value = "false"; // Đặt giá trị là file
		} else {
		    isFolderInput.value = "true"; // Đặt giá trị là folder
		}
		
		// Sự kiện input: Kiểm tra tên tệp mỗi khi người dùng nhập liệu
		renameInput.addEventListener("input", function() {
		    const inputName = this.value;
		    let isValid = true; // Flag to track validity
		
		    // Nếu là file
		    if (isFolderInput.value === "false") {
		        if (invalidCharsPattern.test(inputName)) {
		            renameNotice.textContent = "File name can't contain any of the following characters:\n \\ / : * ? \" < > |";
		            renameNotice.parentElement.classList.add("visible");
		            isValid = false;
		        } else if (!fileNamePattern.test(inputName)) {
		            renameNotice.textContent = "Filename is invalid.";
		            renameNotice.parentElement.classList.add("visible");
		            isValid = false;
		        } else {
		            renameNotice.parentElement.classList.remove("visible");
		        }
		    }
		    // Nếu là folder
		    else if (isFolderInput.value === "true") {
		        if (invalidCharsPattern.test(inputName)) {
		            renameNotice.textContent = "Folder name can't contain any of the following characters:\n \\ / : * ? \" < > |";
		            renameNotice.parentElement.classList.add("visible");
		            isValid = false;
		        } else if (!folderNamePattern.test(inputName)) {
		            renameNotice.textContent = "Folder name is invalid.";
		            renameNotice.parentElement.classList.add("visible");
		            isValid = false;
		        } else {
		            renameNotice.parentElement.classList.remove("visible");
		        }
		    }
		
		    // Enable/disable the confirm button based on validity
		    confirmRenameButton.disabled = !isValid;
		});

			
	document.getElementById('cancel-rename-modal-btn').addEventListener('click', function() {
	    document.getElementById('renameModal').classList.remove('open'); // Xóa class .open để ẩn modal
	});
	
	window.onclick = function(e) {
		if(e.target == renameModal) {
			renameModal.classList.remove('open');
		}
	}
	
	 const shareModal = document.getElementById('shareModal');
	 
	 document.querySelectorAll('.share-btn').forEach(button => {
	    button.addEventListener('click', function(event) {
	        event.preventDefault();
	        shareModal.style.display = "flex";
	    });
	});
	
	document.querySelector('#cancel-share-modal-btn').addEventListener('click', function() {
		shareModal.style.display = "none";
	})
	
	window.onclick = (e) => {
        if (e.target === shareModal) {
            shareModal.style.display = "none";
        }
    };
	
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

/*document.addEventListener('click', function(event) {
    const notifyBell = document.querySelector('.notify');
    const notifyContainer = document.querySelector('.notify-block');

    if (!notifyBell.contains(event.target) && !notifyContainer.contains(event.target)) {
        notifyContainer.classList.remove('open'); 
    }
});

document.querySelector('.notify').addEventListener('click', function(e) {
    const notifyContainer = document.querySelector('.notify-block'); 

    notifyContainer.classList.toggle('open');
    
    e.stopPropagation();
});*/

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

document.querySelectorAll('.delete-form').forEach(form => {
    form.addEventListener('submit', function(event) {
        event.preventDefault();
        document.querySelector('.loading-container').style.display = 'flex';
        form.submit();
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const fileInputs = document.querySelectorAll('.uploadItem');

    fileInputs.forEach(input => {
        input.addEventListener('change', async function() {
            if (this.files.length > 0) {
                var fd = new FormData();
                var files = this.files;
                var count = 0;
                var size = 0;

                document.querySelector('.loading-container').style.display = 'flex';
                for (var i = 0; i < files.length; i++) {
                    count++;
                    size += files[i].size;
                    fd.append("attachment", files[i]); // Sử dụng "attachments[]" để gửi như một mảng
                }

                const preUploadResponse = await fetch (`/PBL4/checkifuploadpossible?size=${size}&fileQuantity=${count}`);

                const preUploadReturnObject = await preUploadResponse.json();

                if (preUploadReturnObject.message === 'Upload possible!'){
                    const form = this.closest('form');
                    var url = form.action;
                    
                    fetch(url,{
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
                            document.querySelector('.loading-container').style.display = 'none';
                            setTimeout(function() {
                                alert(returnObject.message);
                                if (returnObject.message === 'Uploaded successfully!') {
                                    window.location.reload();
                                }
                            }, 100);	
                        })
                        .catch(function(error){
                            console.log(error);
                        }) 
                }
                else{
                    document.querySelector('.loading-container').style.display = 'none';
                    setTimeout(function() {
                        alert(preUploadReturnObject.message);
                    }, 100);
                }
            }
        });
    });
});

cancelNewFolderModalBtn.onclick = function() {
    newFolderModal.classList.remove('open');
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
    
    fetch('/PBL4/sendmail',{
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
            if (returnObject.message === 'Sent Successfully!'){
                document.getElementById("composeModal").style.display = 'none';
                alert("Sent successfully!");

                var receiverUsername = document.getElementById('to').value;
                var content = `${username}` + " has sent you a letter: " + document.getElementById('subject').value;
                var sentUsername = `${username}`;
                var accessLink = `/PBL4/userhomepage/mail/readmail?mailId=${returnObject.mailId}`;

                sendMailNotification(receiverUsername, content, sentUsername, accessLink);
                updateProgress();


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

    fetch('/PBL4/createnewfolder',{
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
                window.location.href = `/PBL4/userhomepage/main?folderPath=${encodedFolderPath}`;
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
        url = '/PBL4/renamefolder';
    }
    else if (isFolder === 'false'){
        url = '/PBL4/renamefile';
    }

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
                window.location.href = `/PBL4/userhomepage/main?folderPath=${encodedFolderPath}`;
			}
		})
		.catch(function(error){
			console.log(error);
		})
})

        