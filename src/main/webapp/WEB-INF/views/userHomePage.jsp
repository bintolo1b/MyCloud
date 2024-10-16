<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons" />
<link rel="stylesheet"
	href="<c:url value='/assets/css/userHomePage.css'/>" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link href="https://fonts.googleapis.com/css2?family=Google+Sans:wght@400;500;700&display=swap" rel="stylesheet">
<script src="<c:url value='/assets/js/userHomePageFetch.js'/>"></script>
<title>Insert title here</title>
</head>
<body>

	<div class="navbar-fixed">
		<nav class="nav-extended" style="background-color: #f8fafd">
			<div class="nav-wrapper">
				<ul>
					<li><a href='<c:url value='/userhomepage'/>' class="title grey-text text-darken-1">My Cloud</a></li>
				</ul>
				<div class="search-wrapper">
					<i class="material-icons">search</i> <input type="search"
						name="Search" placeholder="Search Drive" />
				</div>
				<ul class="right">
					<li>
						<a href="#!"><i class="material-icons grey-text text-darken-1">settings</i></a>
						<span class="detail-item">Settings</span>
					</li>
					<li>
						<a href="#!" class="notify"><i class="material-icons grey-text text-darken-1">notifications</i></a>
						<span class="detail-item">Notifications</span>
						<div class="notify-block">
							<h3>You don't have any notifications</h3>
						</div>
					</li>
					<li class="account"><a href="#!"><img
							src='<c:url value='/assets/img/user.png'/>' alt="profile pic" class="circle" /></a>
							<ul class="listItems">
								<li class="item">
									<a href="#">Personal Information</a>
								</li>
								<li class="item">
									<a href="#">Change Password</a>
								</li>
								<li class="item">
									<c:url value="/logout" var="logouturl">
									</c:url>
									<form action="${logouturl}" method="post">
										<button class="logout-btn" type="submit">Log Out</button>
									</form>
								</li>
							</ul>
					</li>
				</ul>
			</div>
			<div class="nav-wrapper nav-2">
				<ul>
					<li><a href="#!"
						class="waves-effect waves-light btn btn-flat white-text">New</a>
					</li>
				</ul>
			</div>
		</nav>
	</div>
	<ul class="side-nav fixed floating transparent z-depth-0">
		<li class="userHomePageItem active"><a href="#"><i class="material-icons blue-text text-darken-1">dashboard</i>My Drive</a></li>
		<li class="userHomePageItem" data-file="userHomePageItems/mailItem.jsp"><a href="#"><i class="material-icons">mail</i>Mail</a></li>
		<li class="userHomePageItem" data-file="userHomePageItems/sharedItem.jsp"><a href="#"><i class="material-icons">people</i>Shared with me</a></li>
		<li class="userHomePageItem" data-file="userHomePageItems/recentItem.jsp"><a href="#"><i class="material-icons">access_time</i>Recent</a></li>
		<li class="userHomePageItem" data-file="userHomePageItems/googlePhotoItem.jsp"><a href="#"><i class="material-icons">camera</i>Google Photos</a></li>
		<li class="userHomePageItem" data-file="userHomePageItems/starredItem.jsp"><a href="#"><i class="material-icons">star</i>Starred</a></li>
		<li class="userHomePageItem" data-file="userHomePageItems/trashItem.jsp"><a href="#"><i class="material-icons">delete</i>Trash</a></li>
		<li><div class="divider"></div></li>
		<li class="userHomePageItem" data-file="userHomePageItems/backUpItem.jsp"><a href="#"><i class="material-icons">cloud</i>Backup</a></li>
		<li><div class="divider"></div></li>
		<li class="userHomePageItem" data-file="userHomePageItems/upgradeStorageItem.jsp"><a href="#"><i class="material-icons">storage</i>Upgrade Storage</a></li>
	</ul>

<!-- Hiển thị breadcrumb -->
	  
	<div class="main">
		<div class="container-fluid">
			<% 
			    String folderPath = (String) request.getAttribute("folderPath");
			
			    String displayPath = folderPath.replace("D:\\MyPBL4Server\\", "");
			
			    String[] pathSegments = displayPath.split("\\\\");
			%>
			
			<% if (pathSegments.length > 0) { %>  
			    <div class="breadcrumb-container" style="margin-bottom: 10px;">
			        <%
			        String cumulativePath = "D:\\MyPBL4Server";
			        for (int i = 0; i < pathSegments.length; i++) {
			            cumulativePath += "\\" + pathSegments[i];
			
			            String displaySegment = (i == 0) ? "MyCloud" : pathSegments[i];
			        %>
			            <c:url value="/userhomepage" var="breadcrumbUrl">
			                <c:param name="folderPath" value="<%= cumulativePath %>"></c:param>
			            </c:url>
			            <a href="${breadcrumbUrl}" class="breadcrumb-link">
			                <%= displaySegment %>
			            </a>
			            <% if (i < pathSegments.length - 1) { %>
			                <i class="breadcrumb-separator material-icons">arrow_forward_ios</i>
			            <% } %>
			        <% } %>
			    </div>
			<% } %>

			<div class = "folder-container">
				<p class="subheader">Folders</p>
				<c:forEach items="${subFolders}" var="folder">
					<c:url value="/userhomepage" var="userhomepageurl">
							<c:param name="folderPath" value="${folder.path}"></c:param>
					</c:url>
					<div onclick="window.location.href='${userhomepageurl}'" class="card-panel folder">
						<i class="material-icons left">folder</i>
						<span>${folder.name}</span>
						<div class="kebab-wrapper">
                            <div class="kebab-container">
                            <i class="kebab-menu fa-solid fa-ellipsis-vertical"></i>
                            <ul class="kebab-items-list">
                                <li class="kebab-item">
                                    <a href="">
                                        <i class="material-icons">download</i>
                                        Download
                                    </a>
                                </li>
                                <li class="kebab-item">
                                    <a href="">
                                        <i class="material-icons">share</i>
                                        Share
                                    </a>
                                </li>
                                <li class="kebab-item">
                                    <a href="">
                                        <i class="material-icons">edit</i>
                                        Rename
                                    </a>
                                </li>
                                <li class="kebab-item">
                                    <i class="material-icons" style="margin-left:15px">delete</i>
                                    <c:url value="/deletefoldercontroller" var="deletefolderurl">
										<c:param name="folderPath" value="${folderPath}"></c:param>
										<c:param name="deletedFolderName" value="${folder.name}"></c:param>
									</c:url>
									<form action="${deletefolderurl}" method="post">
									    <input type="submit" value="Delete">
									</form>
                                </li>
                            </ul>
                        </div>
                        </div>
					</div>
				</c:forEach>
			</div>

			<div class="file-container">
		    <p class="subheader">Files</p>
		    <c:forEach items="${files}" var="file" varStatus="status">
		        <c:url value="/displayfilecontroller" var="displayfileurl">
		            <c:param name="folderPath" value="${folderPath}"></c:param>
		            <c:param name="fileName" value="${file.name}"></c:param>
		        </c:url>
		
		        <div data-url="${displayfileurl}" class="card-panel file">
		            <!-- Div chứa tên file, icon và kebab menu -->
		            <div class="file-details">
		                <i class="material-icons left">description</i>
		                <span>${file.name}</span>
		                
		                <div class="kebab-wrapper">
		                    <div class="kebab-container">
		                        <i class="kebab-menu fa-solid fa-ellipsis-vertical"></i>
		                        <ul class="kebab-items-list">
		                            <li class="kebab-item">
		                                <a href="">
		                                    <i class="material-icons">download</i>
		                                   	Download
		                                </a>
		                            </li>
		                            <li class="kebab-item">
		                                <a href="">
		                                    <i class="material-icons">share</i>
		                                    Share
		                                </a>
		                            </li>
		                            <li class="kebab-item">
		                                <a href="">
		                                    <i class="material-icons">edit</i>
		                                   	Rename
		                                </a>
		                            </li>
		                            <li class="kebab-item">
		                                <i class="material-icons" style="margin-left:15px">delete</i>
		                                <c:url value="/deletefilecontroller" var="deletefilerurl">
		                                    <c:param name="folderPath" value="${folderPath}"></c:param>
		                                    <c:param name="deletedFileName" value="${file.name}"></c:param>
		                                </c:url>
		                                <form action="${deletefilerurl}" method="post">
		                                    <input type="submit" value="Delete">
		                                </form>
		                            </li>
		                        </ul>
		                    </div>
		                </div>
	            	</div>

			            <div class="preview-panel">
						    <img src="	" alt="" class="file-preview-image" id="img-${status.index}-${file.name}">
						</div>
						
						
						<script>
	                        var folderPath = "${folderPath.replace('\\', '\\\\')}";
	                      	var fileName = "${file.name}";
	                      	var imgTagId = "img-${status.index}-${file.name}"; 
	                      	console.log(imgTagId+" tren")
	                      	assignPDFImgToImgTag(folderPath, fileName, imgTagId);
                     	</script>
			        </div>
			    </c:forEach>

			</div>
		</div>
		<button id="composeBtn" class="compose-btn">
    		<i class="fas fa-envelope"></i> Compose a letter
		</button>

		
		 <div id="composeModal" class="composeModal">
	        <div class="composeModal-content">
	            <span id="closeComposeModal" class="closeComposeModal">&times;</span>
	            <h2>Compose a letter</h2>
	            <form>
	                <label class="composeLabel" for="to">To:</label>
	                <input type="email" id="to" name="to" placeholder="Address of receiver" required>
	
	                <label class="composeLabel" for="subject">Theme:</label>
	                <input type="text" id="subject" name="subject" placeholder="Theme" required>
	
	                <label class="composeLabel" for="message">Content:</label>
					<textarea id="message" name="message" rows="15" style="height: 150px;" placeholder="Content..."></textarea>

	
	                <div class="compose-form-buttons">
	                    <button type="button" class="attach-btn">
	                        <i class="fas fa-paperclip"></i> Attach
	                        <input type="file" id="attachment" name="attachment" style="display: none;">
	                    </button>
	                    <button type="submit" class="send-btn">Send</button>
	                </div>
	            </form>
	        </div>
	    </div>
	</div>
	
	<div class="modal js-modal">
        <div class="modal-container js-modal-container">
            <div class = "modal-close js-modal-close">
                <i class="material-icons">close</i>
            </div>
			<header class="modal-header"></header>
			  <ul class="new-list">
                <li class="new-item">
                    <a href="">
                        <i class="FFicon material-icons">create_new_folder</i>
                        Create new folder
                    </a>
                </li>
                <li class="new-item">
                     <a href="">
                        <i class="FFicon material-icons">drive_folder_upload</i>
                        Upload folder
                    </a>
                    <c:url value="/uploadfoldercontroller" var="uploadfolderurl">
						<c:param name="folderPath" value="${folderPath}"></c:param>
					</c:url>
					<form action="${uploadfolderurl}" method="post" enctype="multipart/form-data">
					    <input type="file" name="files" webkitdirectory directory multiple>
					</form>
                </li>
                <li class="new-item">
                    <a href="">
                        <i class="FFicon material-icons">upload_file</i>
                        Upload file
                    </a> 
					<c:url value="/uploadfilecontroller" var="uploadfileurl">
						<c:param name="folderPath" value="${folderPath}"></c:param>
					</c:url>
					<form action="${uploadfileurl}" method="post" enctype="multipart/form-data">
						<input type="file" name="files" multiple required="required"/>
					</form>
                </li>
            </ul>
        </div>
    </div>
    
    <div class = "display-modal">
    	<iframe scr = ""></iframe>
    </div>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
<script>
document.addEventListener('DOMContentLoaded', function() { 
    const initialMainContent = document.querySelector('.main').innerHTML;

    let currentIntervalIds = []; // Mảng để lưu trữ các intervalId hiện tại
	
    initializeMainContent();

    document.querySelectorAll('.userHomePageItem').forEach(item => {
        item.addEventListener('click', function(event) {
            event.preventDefault();

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

            const file = this.getAttribute('data-file');

            // Clear all intervals
            clearAllIntervals();

            // Nếu là item đầu tiên, trả về nội dung ban đầu
            if (this === document.querySelector('.userHomePageItem:first-child')) {
                document.querySelector('.main').innerHTML = initialMainContent;
                initializeMainContent();
            } else if (file) {
                // Sử dụng AJAX để lấy nội dung file
                loadFileContent(file);
            } else {
                console.error('File value is empty, not fetching.');
                document.querySelector('.main').innerHTML = "<p>File không hợp lệ. Vui lòng thử lại.</p>";
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

    function loadFileContent(file) {
        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
                document.querySelector('.main').innerHTML = this.responseText;

                // Execute any scripts that are in the new content
                const newContent = document.querySelector('.main');
                const scripts = newContent.getElementsByTagName('script');
                for (var i = 0; i < scripts.length; i++) {
                    new Function(scripts[i].innerText)();
                }
            }
        };
        xhr.open("GET", file, true);
        xhr.send();
    }
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
        const fileName = card.querySelector('.file-details span').innerText;
        console.log(folderPath)
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
      </script>
      
</html>