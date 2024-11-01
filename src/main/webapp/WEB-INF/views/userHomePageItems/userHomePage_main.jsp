<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script src="<c:url value='/assets/js/userHomePage_main_getImg.js'/>"></script>
	<div class="main" id="main">
		<div class="container-fluid">
			<% 
			    String folderPath = (String) request.getAttribute("folderPath");
			
			    String displayPath = folderPath.replace("D:\\MyPBL4Server\\UserFolder\\", "");
			
			    String[] pathSegments = displayPath.split("\\\\");
			%>
			
			<% if (pathSegments.length > 0) { %>  
			    <div class="breadcrumb-container" style="margin-bottom: 10px;">
			        <%
			        String cumulativePath = "D:\\MyPBL4Server\\UserFolder";
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
                                    <a href="">
                                        <i class="material-icons">share</i>
                                        Share
                                    </a>
                                </li>
                                <li class="kebab-item">
                                    <a href="#" class="rename-btn" data-file-name="${folder.name}">
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
		                                <a href="">
		                                    <i class="material-icons">share</i>
		                                    Share
		                                </a>
		                            </li>
		                            <li class="kebab-item">
		                                <a href="#" class="rename-btn" data-file-name="${file.name}">
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
                    <c:url value="/uploadfoldercontroller" var="uploadfolderurl">
						<c:param name="folderPath" value="${folderPath}"></c:param>
					</c:url>
					<form action="${uploadfolderurl}" method="post" enctype="multipart/form-data">
					    <input class="uploadItem" type="file" name="files" webkitdirectory directory multiple>
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
	        <!-- Thay đổi từ div sang form -->
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
	                
	                <div class="suggest-receiver-container">
	                	<ul class="suggest-receivers-list">
	                		<li class="suggest-receiver-item">
	                			<a href="#!"><img src='<c:url value='/assets/img/user.png'/>' alt="profile pic" class="receiverAvt" /></a>
	                			<p class="receiver-username">username1</p>
	                		</li>
	                		<li class="suggest-receiver-item">
	                			<a href="#!"><img src='<c:url value='/assets/img/user.png'/>' alt="profile pic" class="receiverAvt" /></a>
	                			<p class="receiver-username">username2</p>
	                		</li>
	                	</ul>
	                </div>
	
	                <label class="composeLabel" for="subject">Theme:</label>
	                <input type="text" id="subject" name="subject" placeholder="Theme" required>
	
	                <label class="composeLabel" for="message">Content:</label>
					<textarea id="message" name="message" rows="15" style="height: 150px;" placeholder="Content..."></textarea>
					
					<div class="attach-file">
						<span class="attach-file-name">File1</span>
						<span id="close-attach-file" class="close-attach-file">&times;</span>
					</div>

	                <div class="compose-form-buttons">
	                    <button type="button" class="attach-btn">
	                        <i class="fas fa-paperclip"></i> Attach
	                        <input type="file" id="attachment" name="attachment" multiple>
	                    </button>
	                   		
	                    <button type="submit" class="send-btn">Send</button>
	                </div>
	            </form>
	        </div>
	    </div>
	</div>	
    
    <script src="<c:url value='/assets/js/userHomePage_main.js'/>"></script>
</body>
</html>