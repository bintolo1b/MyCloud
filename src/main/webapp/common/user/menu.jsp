<ul class="side-nav fixed floating transparent z-depth-0">
		<li class="userHomePageItem">
			<a href="<c:url value='/userhomepage/main'/>">
				<i class="material-icons">dashboard</i>My Drive
			</a>
		</li>
		
		<li class="userHomePageItem">
			<a href="<c:url value='/userhomepage/mail'/>">
				<i class="material-icons">mail</i>Mail
			</a>
		</li>
		
		<li class="userHomePageItem">
			<a href="<c:url value='/userhomepage/outbox'/>">
				<i class="material-icons">outbox</i>Outbox
			</a>
		</li>
		<!-- <li class="userHomePageItem"><a href="#"><i class="material-icons">people</i>Shared with me</a></li>
		<li class="userHomePageItem"><a href="#"><i class="material-icons">access_time</i>Recent</a></li>
		<li class="userHomePageItem"><a href="#"><i class="material-icons">star</i>Starred</a></li>
		<li class="userHomePageItem"><a href="#"><i class="material-icons">delete</i>Trash</a></li>
		<li><div class="divider"></div></li>
		<li class="userHomePageItem"><a href="#"><i class="material-icons">cloud</i>Backup</a></li>
		<li><div class="divider"></div></li>
		<li class="userHomePageItem"><a href="#"><i class="material-icons">storage</i>Upgrade Storage</a></li> -->
		<div class="circular-progress">
			<span class="progress-value">0%</span>
		</div>
		<div id="used-space-percent"></div>
</ul>

<script>
	const username= "${user.username}";
</script>