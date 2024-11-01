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
				<ul id="suggestionsList">
					<li>
						<img alt="" src="<c:url value='/assets/img/wordImage.png'/>" class="suggestFileImg">
						<div class="suggestInfor">
							<div class="suggestInfor_first">
								<span class="suggestFileName">File 1</span>
								<span class="suggestFileAuthor">Author</span>
							</div>
							<div class="SuggestInfor_second">							
								<span class="suggestFileDate">2024/10/29</span>   
							</div>
						</div>
					</li>
					<li>
						<img alt="" src="<c:url value='/assets/img/pdfImage.png'/>" class="suggestFileImg">
						<div class="suggestInfor">
							<div class="suggestInfor_first">
								<span class="suggestFileName">File 2</span>
								<span class="suggestFileAuthor">Author</span>
							</div>
							<div class="SuggestInfor_second">							
								<span class="suggestFileDate">2024/10/29</span>   
							</div>
						</div>
					</li>
					<li>
						<img alt="" src="<c:url value='/assets/img/jpegImage.png'/>" class="suggestFileImg">
						<div class="suggestInfor">
							<div class="suggestInfor_first">
								<span class="suggestFileName">File 3</span>
								<span class="suggestFileAuthor">Author</span>
							</div>
							<div class="SuggestInfor_second">							
								<span class="suggestFileDate">2024/10/29</span>   
							</div>
						</div>
					</li>					
				</ul>
				<ul class="right">
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