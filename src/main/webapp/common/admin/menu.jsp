<div class="sidenav">
	<a>
		<img class="home_logo" alt="" src="<c:url value='/assets/img/mycloud_home.png'/>">
	</a>
	<a href="/PBL4/admin/home" class="admin_item" onclick="showSection('home', this)">
		<i class="material-icons grey-text text-darken-1">home</i>
	Home</a>
	<a href="/PBL4/admin/updateuserpassword" class="admin_item" onclick="showSection('update-password', this)">
		<i class="material-icons grey-text text-darken-1">lock_reset</i>
	Update Password</a>
	<a href="/PBL4/admin/addcapacity" class="admin_item" onclick="showSection('add-storage', this)">
		<i class="material-icons grey-text text-darken-1">memory</i>
	Add Capacity</a>

	<div class="circular-progress">
		<span class="progress-value">0%</span>
	</div>
	<div id="used-space-percent"></div>
	
	<form action="/PBL4/logout" method="POST" class="logout_form">
		<button type="submit" class="logout_button">
			<i class="material-icons grey-text text-darken-1">logout</i>
			Logout
		</button>
	</form>
</div>