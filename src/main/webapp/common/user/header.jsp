<div class="navbar-fixed">
		<nav class="nav-extended" style="background-color: #f8fafd">
			<div class="nav-wrapper">
				<ul>
					<li>
						<a href='<c:url value='/userhomepage/main'/>'>
							<img class="home_logo" alt="" src="<c:url value='/assets/img/mycloud_home.png'/>">
						</a>
					</li>
				</ul>
				<div class="search-wrapper">
					<i class="material-icons search-icon">search</i> <input type="search"
						name="Search" placeholder="Search Drive" autocomplete="off" />
					<ul id="suggestionsList">					
					</ul>
				</div>
				
				<ul class="right">
					<li>
						<span class="user-fullname">${user.fullName}</span>
					</li>
					<li>
						<a href="#!" class="notify"><i class="material-icons grey-text text-darken-1">notifications</i></a>
						<span class="new-notify"></span>
						<span class="detail-item">Notifications</span>
						<div class="notify-block">
							<!-- <h3>You don't have any notifications</h3> -->
							<ul id="notifyList">
							<c:forEach items="${notifications}" var="notify">
								<li class="notify-item ${notify.status}" data-id="${notify.id}" data-url="${notify.accessLink}">
									<img alt="" src="/PBL4/getAvatar?username=${notify.sentUsername}" class="notifyAvt">
									<div class="notifyInfor">
										<div class="notifyInfor_first">
											<span class="notifyContent">${notify.content}</span>
										</div>
										<div class="notifyInfor_second">
											<span class="notifyDate">${notify.getFormattedTime()}</span>
										</div>
									</div>
								</li>
							</c:forEach>							
						</ul>
						</div>
					</li>
					<li class="account"><a href="#!"><img
							src='/PBL4/getAvatar?username=${user.username}' alt="profile pic" class="circle avatar" /></a>
							<ul class="listItems">
								<li class="item">
								    <c:url value='/userinformation' var='userinforurl'>
								    </c:url>
								    <a href="${userinforurl}" target="_blank">Personal Information</a>
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