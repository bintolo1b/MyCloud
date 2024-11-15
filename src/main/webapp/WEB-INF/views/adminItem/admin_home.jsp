<%@include file="/common/taglib.jsp"%>

<link href="<c:url value='/assets/css/admin_home.css'/>" rel="stylesheet">
<div class="main-content">
    <!-- Home Section -->
    <div id="home" class="container">
        <h1>List of users</h1>
        <table class="user-table">
            <thead>
                <tr>
                    <th>Username</th>
                    <th>Fullname</th>
                    <th>Status</th>
                    <th>Capacity</th>
                    <th>Role</th>
                </tr>
            </thead>
            <tbody>
            	<c:forEach var="user" items="${users}">
					<tr>
	                    <td>${user.username}</td>
	                    <td>${user.fullName}</td>
	                    <td><span data-username="${user.username}" class="status ${user.status}">${user.status}</span></td>
	                    <c:choose>
				            <c:when test="${user.role == 'admin'}">
				                <td>-</td>
				            </c:when>
				            <c:otherwise>
				                <td>${user.totalCapacityUsed} GB / ${user.totalCapacity} GB <span style="color: chocolate;">(${user.percentCapacityUsed}%)</span></td>
				            </c:otherwise>
				        </c:choose>	
	                    <td>${user.role}</td>
               		</tr>
				</c:forEach>
            </tbody>
        </table>
    </div>
</div>
<script src="<c:url value='/assets/js/admin_home.js'/>"></script>
