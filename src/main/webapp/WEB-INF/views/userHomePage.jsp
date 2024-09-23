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

<title>Insert title here</title>
</head>
<body>
	<%-- <h1>welcome to google drive clone</h1>
	<a href="<%=request.getContextPath()%>/FileController">Start Browsing your files</a> --%>

	<div class="navbar-fixed">
		<nav class="nav-extended white">
			<div class="nav-wrapper white">
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
		<li class="active"><a href="#"><i
				class="material-icons blue-text text-darken-1">dashboard</i>My Drive</a>
		</li>
		<li><a href="#"><i class="material-icons">devices</i>Computers</a>
		</li>
		<li><a href="#"><i class="material-icons">people</i>Shared
				with me</a></li>
		<li><a href="#"><i class="material-icons">access_time</i>Recent</a>
		</li>
		<li><a href="#"><i class="material-icons">camera</i>Google
				Photos</a></li>
		<li><a href="#"><i class="material-icons">star</i>Starred</a></li>
		<li><a href="#"><i class="material-icons">delete</i>Trash</a></li>
		<li><div class="divider"></div></li>
		<li><a href="#"><i class="material-icons">cloud</i>Backup</a></li>
		<li><div class="divider"></div></li>
		<li><a href="#"><i class="material-icons">storage</i>Upgrade
				Storage</a></li>
		
	</ul>
	<div class="main">
		<div class="container-fluid">
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
                                        Tải xuống
                                    </a>
                                </li>
                                <li class="kebab-item">
                                    <a href="">
                                        <i class="material-icons">share</i>
                                        Chia sẻ
                                    </a>
                                </li>
                                <li class="kebab-item">
                                    <a href="">
                                        <i class="material-icons">edit</i>
                                        Đổi tên
                                    </a>
                                </li>
                                <li class="kebab-item">
                                    <!-- <a href="">
                                        <i class="material-icons">delete</i>
                                        Chuyển vào thùng rác
                                    </a> -->
                                    <i class="material-icons" style="margin-left:15px">delete</i>
                                    <c:url value="/deletefoldercontroller" var="deletefolderurl">
										<c:param name="folderPath" value="${folderPath}"></c:param>
										<c:param name="deletedFolderName" value="${folder.name}"></c:param>
									</c:url>
									<form action="${deletefolderurl}" method="post">
									    <input type="submit" value="Chuyển vào thùng rác">
									</form>
                                </li>
                            </ul>
                        </div>
                        </div>
					</div>
				</c:forEach>
			</div>
			
			<div class = "file-container">
				<p class="subheader">Files</p>
				<c:forEach items="${files}" var="file">
					<c:url value="/displayfilecontroller" var="displayfileurl">
						<c:param name="folderPath" value="${folderPath}"></c:param>
						<c:param name="fileName" value="${file.name}"></c:param>
					</c:url>
					<div data-url="${displayfileurl}"class="card-panel file">
						<i class="material-icons left">description</i>
						<span>${file.name}</span>
						<div class="kebab-wrapper">
                            <div class="kebab-container">
                            <i class="kebab-menu fa-solid fa-ellipsis-vertical"></i>
                            <ul class="kebab-items-list">
                                <li class="kebab-item">
                                    <a href="">
                                        <i class="material-icons">download</i>
                                        Tải xuống
                                    </a>
                                </li>
                                <li class="kebab-item">
                                    <a href="">
                                        <i class="material-icons">share</i>
                                        Chia sẻ
                                    </a>
                                </li>
                                <li class="kebab-item">
                                    <a href="">
                                        <i class="material-icons">edit</i>
                                        Đổi tên
                                    </a>
                                </li>
                                <li class="kebab-item">
                                    <!-- <a href="">
                                        <i class="material-icons">delete</i>
                                        Chuyển vào thùng rác
                                    </a> -->
                                    <i class="material-icons" style="margin-left:15px">delete</i>
                                    <c:url value="/deletefilecontroller" var="deletefilerurl">
										<c:param name="folderPath" value="${folderPath}"></c:param>
										<c:param name="deletedFileName" value="${file.name}"></c:param>
									</c:url>
									<form action="${deletefilerurl}" method="post">
									    <input type="submit" value="Chuyển vào thùng rác">
									</form>
                                </li>
                            </ul>
                        </div>
                        </div>
                      </div>
				</c:forEach>
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

		document.addEventListener('click', function(event) {
		    const notifyIcon = document.querySelector('.notify');
		    const notifyBlock = document.querySelector('.notify-block');
		
		    // Check if the click is outside the notify icon and notify block
		    if (!notifyIcon.contains(event.target) && !notifyBlock.contains(event.target)) {
		        notifyBlock.classList.remove('open'); // Hide the notify block
		    }
		});
		
		document.querySelector('.notify').addEventListener('click', function(e) {
		    const notifyBlock = document.querySelector('.notify-block'); // Select the .notify-block
		    console.log(notifyBlock);
		
			notifyBlock.classList.toggle('open');
			
		    e.stopPropagation();
		});


      document.querySelectorAll('.kebab-container').forEach(container => {
    	    container.addEventListener('click', function(e) {
    	        e.stopPropagation(); // Ngăn sự kiện click tiếp tục lên các phần tử cha

    	        const menu = this.querySelector('.kebab-items-list');
    	        const isOpen = menu.style.display === 'block';

    	        // Đóng tất cả các menu trước
    	        document.querySelectorAll('.kebab-items-list').forEach(m => {
    	            m.style.display = 'none';
    	        });

    	        // Mở hoặc đóng menu hiện tại
    	        menu.style.display = isOpen ? 'none' : 'block';
    	    });
    	});

    	// Đóng tất cả các menu khi nhấp ra ngoài
    	document.addEventListener('click', function(event) {
    	    document.querySelectorAll('.kebab-items-list').forEach(menu => {
    	        if (!menu.parentElement.contains(event.target)) {
    	            menu.style.display = 'none';
    	        }
    	    });
    	});

        const newBtn = document.querySelector('a.waves-effect.waves-light.btn.btn-flat.white-text');
        const modal = document.querySelector('.js-modal');
        const modalClose = document.querySelector('.js-modal-close');
        const modalContainer = document.querySelector('.js-modal-container');

        // Hiển thị modal
        function showModal() {
            modal.classList.add('open');
        }

        // Ẩn modal
        function hideModal() {
            modal.classList.remove('open');
        }

        // Gán sự kiện mở modal
        newBtn.addEventListener('click', showModal);

        // Gán sự kiện đóng modal khi click vào nút đóng
        modalClose.addEventListener('click', hideModal);

        // Gán sự kiện đóng modal khi click ra ngoài modal-container
        modal.addEventListener('click', hideModal);

        // Ngăn không cho sự kiện click của modal-container lan ra ngoài modal
        modalContainer.addEventListener('click', function(event) {
            event.stopPropagation();
        });
        
        
        document.querySelectorAll('.new-item').forEach(item => {
            item.addEventListener('click', function(e) {
                const inputFile = this.querySelector('input[type="file"]');

                // Ngăn sự kiện mặc định cho các thẻ con (nếu có) như <a>
                if (e.target.tagName.toLowerCase() === 'a' || e.target.tagName.toLowerCase() === 'i') {
                    e.preventDefault(); 
                }

                // Nếu có input file, kích hoạt nó
                if (inputFile) {
                    inputFile.click();
                }
            });
        });

        document.addEventListener('DOMContentLoaded', function() {
            // Tự động submit form khi file hoặc folder được chọn
            const fileInputs = document.querySelectorAll('input[type="file"]');

            fileInputs.forEach(input => {
                input.addEventListener('change', function() {
                    if (this.files.length > 0) {
                        // Tìm form gần nhất và submit
                        const form = this.closest('form');
                        form.submit();
                    }
                });
            });
        });
        
        document.querySelectorAll('.card-panel.file').forEach(card => {
            card.addEventListener('dblclick', function() {
                const url = this.getAttribute('data-url');
                const displayModal = document.querySelector('.display-modal');
                const iframe = document.querySelector('.display-modal iframe');

                // Kiểm tra nếu file là ảnh (jpg, png, gif...)
                if (url.match(/\.(jpeg|jpg|gif|png)$/i)) {
                    const img = new Image();
                    img.src = url;

                    img.onload = function() {
                        displayModal.innerHTML = ''; // Xóa nội dung cũ
                        displayModal.appendChild(img);
                        img.style.display = 'block';
                        img.style.margin = 'auto';

                        // Hiển thị modal
                        displayModal.style.display = 'flex';
                        displayModal.classList.add('open');
                    };
                } else {
                    // Mở file lớn trong tab mới
                    window.open(url, '_blank');
                }

                // Đóng modal khi click ra ngoài vùng ảnh
                displayModal.addEventListener('click', function(e) {
                    if (e.target === displayModal) {
                        displayModal.classList.remove('open');
                        displayModal.style.display = 'none';
                    }
                });
            });
        });



      </script>
      
</html>