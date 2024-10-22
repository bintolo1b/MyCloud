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
    	<iframe scr =""></iframe>
    </div>
    
    <script src="<c:url value='/assets/js/userHomePage_main.js'/>"></script>
</body>
</html>