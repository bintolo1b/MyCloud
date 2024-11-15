<%@include file="/common/taglib.jsp"%>

<link rel="stylesheet" href="<c:url value='/assets/css/admin_updatePassword.css'/>" />
    <div class="main-content">
    <div id="update-password" class="container">
        <h1>Update User Password</h1>
        <form class="update-password-form">
            <div class="form-control">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" onblur="showPasswordInputs()" required>
            </div>

            <div id="password-inputs" class="hidden">
                <div class="form-control" id="user-details">
                    <label>User Name:</label>
                    <input id="user-fullname" disabled>
                </div>
            
                <div class="form-control">
                    <label for="new-password">New Password:</label>
                    <input type="password" id="new-password" name="new-password" required>
                </div>

                <div class="form-control">
                    <label for="confirm-password">Verify New Password:</label>
                    <input type="password" id="confirm-password" name="confirm-password" required>
                </div>

                <button type="submit" class="save-password-change">Save Changes</button>
            </div>
        </form>
    </div>
</div>
<script src="<c:url value='/assets/js/admin_updatePassword.js'/>"></script>
