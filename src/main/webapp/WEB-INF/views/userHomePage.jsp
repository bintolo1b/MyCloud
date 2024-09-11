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
<title>Insert title here</title>
</head>
<body>
	<%-- <h1>welcome to google drive clone</h1>
	<a href="<%=request.getContextPath()%>/FileController">Start Browsing your files</a> --%>

	<div class="navbar-fixed">
		<nav class="nav-extended white">
			<div class="nav-wrapper white">
				<ul>
					<li><a href="#!" class="title grey-text text-darken-1">My Cloud</a></li>
				</ul>
				<div class="search-wrapper">
					<i class="material-icons">search</i> <input type="search"
						name="Search" placeholder="Search Drive" />
				</div>
				<ul class="right">
					<li><a href="#!"><i
							class="material-icons grey-text text-darken-1">apps</i></a></li>
					<li><a href="#!"><i
							class="material-icons grey-text text-darken-1">notifications</i></a>
					</li>
					<li><a href="#!"><img
							src='<c:url value='/assets/img/user.png'/>' alt="profile pic" class="circle" /></a>
					</li>
				</ul>
			</div>
			<div class="nav-wrapper nav-2">
				<ul>
					<li><a href="#!"
						class="waves-effect waves-light btn btn-flat white-text">New</a>
					</li>
				</ul>
				<ul class="right">
					<li><a href="#!"><i
							class="material-icons grey-text text-darken-1">view_list</i></a>
					</li>
					<li><a href="#!"><i
							class="material-icons grey-text text-darken-1">info</i></a></li>
					<li><a href="#!"><i
							class="material-icons grey-text text-darken-1">settings</i></a>
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
		<c:url value="/logout" var="logouturl">
		</c:url>
		<form action="${logouturl}" method="post">
			<button type="submit">logout</button>
		</form>
	</ul>
	<div class="main">
		<div class="container-fluid">
			<div class = "folder-container">
				<p class="subheader">Folders</p>
				<c:forEach items="${folders}" var="folder">
					<div class="card-panel folder">
						<i class="material-icons left">folder</i>
						${folder}
					</div>
				</c:forEach>
			</div>
			
			<div class = "file-container">
				<p class="subheader">Files</p>
				<c:forEach items="${files}" var="file">
					<div class="card-panel file">
						<i class="material-icons left">description</i>
						${file}
					</div>
				</c:forEach>

			</div>
		</div>
	</div>
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</html>