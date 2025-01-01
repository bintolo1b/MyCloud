<%@include file="/common/taglib.jsp"%>

<script src="<c:url value='/assets/js/userHomePage_main_getImg.js'/>"></script>
<div class="main" id="main">
	<div class="container-fluid">
		<% 
			String folderPath = (String) request.getAttribute("folderPath");
		
			String displayPath = folderPath.replace("E:\\MyPBL4Server\\UserFolder\\", "");
		
			String[] pathSegments = displayPath.split("\\\\");
		%>
		
		<% if (pathSegments.length > 0) { %>  
			<div class="breadcrumb-container" style="margin-bottom: 10px;">
				<%
				String cumulativePath = "E:\\MyPBL4Server\\UserFolder";
				for (int i = 0; i < pathSegments.length; i++) {
					cumulativePath += "\\" + pathSegments[i];
		
					String displaySegment = (i == 0) ? "MyCloud" : pathSegments[i];
				%>
					<c:url value="/userhomepage/main" var="breadcrumbUrl">
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
				<c:url value="/userhomepage/main" var="userhomepageurl">
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
								<c:url value="/downloadfoldercontroller" var="downloadfolderurl">
									<c:param name="folderPath" value="${folder.path}"></c:param>
								</c:url>
								<a href="${downloadfolderurl}">
									<i class="material-icons">download</i>
									Download
								</a>
							</li>
							<li class="kebab-item">
								<a href="#" class="rename-btn" data-file-name="${folder.name}">
									<i class="material-icons">edit</i>
									Rename
								</a>
							</li>
							<li class="kebab-item">
								<a href="#" class="information-btn" onclick="showFolderFileInformation('${folder.name}','${folder.getFormattedUploadedDate()}','${folder.size}')">
									<i class="material-icons">info</i>
									Folder information
								</a>
							</li>
							<li class="kebab-item">
								<i class="material-icons" style="margin-left:15px">delete</i>
								<c:url value="/deletefoldercontroller" var="deletefolderurl">
									<c:param name="folderPath" value="${folderPath}"></c:param>
									<c:param name="deletedFolderName" value="${folder.name}"></c:param>
								</c:url>
								<form class="delete-form" action="${deletefolderurl}" method="post">
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
				<!-- Div chá»©a tÃªn file, icon vÃ  kebab menu -->
				<div class="file-details">
					<i class="material-icons left">description</i>
					<span>${file.name}</span>
					
					<div class="kebab-wrapper">
						<div class="kebab-container">
							<i class="kebab-menu fa-solid fa-ellipsis-vertical"></i>
							<ul class="kebab-items-list">
								<li class="kebab-item">
									<c:url value="/downloadfilecontroller" var="downloadfileurl">
										<c:param name="folderPath" value="${folderPath}"></c:param>
										<c:param name="downloadedFileName" value="${file.name}"></c:param>
									</c:url>
									<a href="${downloadfileurl}">
										<i class="material-icons">download</i>
										Download
									</a>
								</li>
								<li class="kebab-item">
									<a href="#" class="rename-btn" data-file-name="${file.name}">
										<i class="material-icons">edit</i>
										Rename
									</a>
								</li>
								<li class="kebab-item">
									<a href="#" class="information-btn" onclick="showFolderFileInformation('${file.name}','${file.getFormattedUploadedDate()}','${file.size}')">
										<i class="material-icons">info</i>
										File information
									</a>
								</li>
								<li class="kebab-item">
									<i class="material-icons" style="margin-left:15px">delete</i>
									<c:url value="/deletefilecontroller" var="deletefilerurl">
										<c:param name="folderPath" value="${folderPath}"></c:param>
										<c:param name="deletedFileName" value="${file.name}"></c:param>
									</c:url>
									<form class="delete-form" action="${deletefilerurl}" method="post">
										<input type="submit" value="Delete">
									</form>
								</li>
							</ul>
						</div>
					</div>
				</div>

					<div class="preview-panel">
						<img src="" alt="" class="file-preview-image" id="img-${status.index}-${file.name}">
					</div>
					
					
					<script>
						var folderPathh = "${fn:replace(folderPath, '\\', '\\\\')}";
						var file = "${file.name}";
						var imgTagId = "img-${status.index}-${file.name}";
						assignPDFImgToImgTag(folderPathh, file, imgTagId);
					</script>
				</div>
			</c:forEach>

		</div>
	</div>
	
	<div class="modal js-modal">
	<div class="modal-container js-modal-container">
		<div class = "modal-close js-modal-close">
			<i class="material-icons">close</i>
		</div>
		<header class="modal-header"></header>
			<ul class="new-list">
			<li class="new-item" id="create-new-folder">
				<a href="" id="create-new-folder">
					<i class="FFicon material-icons">create_new_folder</i>
					Create new folder
				</a>
			</li>
			<li class="new-item">
					<a href="">
					<i class="FFicon material-icons">drive_folder_upload</i>
					Upload folder
				</a>
				<c:url value="/uploadfolder" var="uploadfolderurl">
					<c:param name="folderPath" value="${folderPath}"></c:param>
				</c:url>
				<form action="${uploadfolderurl}" enctype="multipart/form-data">
					<input class="uploadItem" type="file" name="files" webkitdirectory directory multiple>
				</form>
			</li>
			<li class="new-item">
				<a href="">
					<i class="FFicon material-icons">upload_file</i>
					Upload file
				</a> 
				<c:url value="/uploadfile" var="uploadfileurl">
					<c:param name="folderPath" value="${folderPath}"></c:param>
				</c:url>
				<form action="${uploadfileurl}" enctype="multipart/form-data">
					<input class="uploadItem" type="file" name="files" multiple required="required"/>
				</form>
			</li>
		</ul>
	</div>
</div>

<div id="newFolderModal" class="newFolderModal" >
	<div class="new-folder-modal-content">
		<h2>New Folder</h2>
		<form id="newFolderForm" method="POST">
			<input type="text" id="folderName" value="New Folder" required="required">
			<input type="hidden" id="folderPath_createFolder" value="${folderPath}">
			<div class="new-folder-modal-buttons">
				<button type="button" id="cancel-new-folder-modal-btn" class="cancel-new-folder-modal-btn">Cancel</button>
				<button type="submit" id="create-new-folder-modal-btn" class="create-new-folder-modal-btn">Create</button>
			</div>
		</form>
	</div>
</div>

<div id="renameModal" class="renameModal">
	<div class="rename-modal-content">
		<h2>Rename</h2>
		<div class="rename-notice">
			<span>A file name can't contain any of the following characters:<br> \ / : * ? " &lt; &gt; |</span>
		</div>
		<!-- Thay Äá»i tá»« div sang form -->
		<form id="renameForm" action="" method="POST">
			<input type="text" id="renameInput" required>
			<input type="hidden" id="oldName" value="">
			<input type="hidden" id="folderPath_rename" value="${folderPath}">
			<input type="hidden" id="isFolder" value="">
			<div class="rename-modal-buttons">
				<button type="button" id="cancel-rename-modal-btn" class="cancel-rename-modal-btn">Cancel</button>
				<button type="submit" id="confirm-rename-modal-btn" class="confirm-rename-modal-btn">OK</button>
			</div>
		</form>
	</div>
</div>

<div class="shareModal" id="shareModal">
	<div class="share-modal-content">
		<h2>Share "Folder"</h2>
		<form action="" method="POST">
			<input type="text" class="usernameInput" placeholder="Enter username to share">
			<h3>Access permit</h3>
			<div class="radio-group">
				<div class="access-group">
					<input type="radio" id="private" name="access" checked> <label class="private-access-label" for="private">Private</label>
				</div>
				<div class="access-group">
					<input type="radio" id="public" name="access"> <label class="public-access-label" for="public">Public</label>
				</div>
			</div>
			<div class="share-modal-button-group">
				<button type="button" id="cancel-share-modal-btn" class="cancel-share-modal-btn">Cancel</button>
				<button type="submit" id="ok-to-share-btn" class="ok-to-share-btn">OK</button>
			</div>
		</form>
	</div>
</div>

<div class="folder-file-information-modal">
  <div class="folder-file-information-modal-content">
    <h2>Information</h2>
    <form>
      <div class="form-group">
        <label for="folder-fileName">Name:</label>
        <input type="text" value="Folder_File name" id="folder-fileName" name="folderName" readonly>
      </div>
      <div class="form-group">
        <label for="uploadDate">Upload Date:</label>
        <input type="text" value="2024/12/31" id="uploadDate" name="uploadDate" readonly>
      </div>
      <div class="form-group">
        <label for="capacity">Capacity:</label>
        <input type="text" value="3MB" id="capacity" name="capacity" readonly>
      </div>
      <div class="modal-actions">
        <button type="button" class="close-modal">Close</button>
      </div>
    </form>
  </div>
</div>


<div class = "display-modal">
	<iframe scr = ""></iframe>
</div>
	
	<button id="composeBtn" class="compose-btn">
		<i class="fas fa-envelope"></i> Compose a letter
	</button>

	
		<div id="composeModal" class="composeModal">
		<div class="composeModal-content">
			<span id="closeComposeModal" class="closeComposeModal">&times;</span>
			<h2>Compose a letter</h2>
			<form id="sendMailForm">
				<label class="composeLabel" for="to">To:</label>
				<input type="text" id="to" name="to" placeholder="Address of receiver" required>
				
				<span class="receiverName">Nguyen Van A</span>

				<label class="composeLabel" for="subject">Theme:</label>
				<input type="text" id="subject" name="subject" placeholder="Theme" required>

				<label class="composeLabel" for="message">Content:</label>
				<textarea id="message" name="message" rows="15" style="height: 150px;" placeholder="Content..."></textarea>
				
					<div id="attachedFilesContainer">
					<!-- File will be appended here -->
				</div>

				<div class="compose-form-buttons">
					<button type="button" class="attach-btn">
						<i class="fas fa-paperclip"></i> Attach
						<input type="file" id="attachment" name="attachment" multiple>
					</button>
						
					<button disabled type="submit" class="send-btn">Send</button>
				</div>
			</form>
		</div>
	</div>
</div>

<div class="loading-container">
	<div class="loader"></div>
</div>	
<script src="<c:url value='/assets/js/userHomePage_main.js'/>"></script>
<script>
	const informationModal = document.querySelector('.folder-file-information-modal');
	
	document.querySelector('.close-modal').addEventListener('click', () => {
	  informationModal.style.display = 'none';
	});
	
	function showFolderFileInformation(name, uploadDate, capacityInBytes) {
		function convertCapacity(capacityInBytes) {
			const bytesInMB = 1024 * 1024; 
			const bytesInGB = 1024 * 1024 * 1024; 

			const capacityInMB = capacityInBytes / bytesInMB;

			if (capacityInMB < 1024) {
				return capacityInMB.toFixed(2) + " MB"; 
			}

			const capacityInGB = capacityInBytes / bytesInGB;
			return capacityInGB.toFixed(2) + " GB"; 
		}
		const finalCapacity = convertCapacity(capacityInBytes);
		document.querySelector('#folder-fileName').value = name;
		document.querySelector('#uploadDate').value = uploadDate;
		document.querySelector('#capacity').value = finalCapacity;
		informationModal.style.display = 'flex';
	}
</script>
