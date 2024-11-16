<%@include file="/common/taglib.jsp"%>

<link rel="stylesheet" href="<c:url value='/assets/css/admin_addCapacity.css'/>" />
<div class="main-content">
    <div id="add-storage" class="container">
        <h1>Add Capacity for User</h1>
        <form class="add-storage-form">
            <div class="form-control">
                <label for="storage-username">Username:</label>
                <input type="text" id="storage-username" name="storage-username" onblur="showUserDetails()" required>
            </div>
            <div id="storage-inputs" class="hidden">
                <div class="form-control" id="user-details">
                    <label>User Name:</label>
                    <input id="user-fullname" disabled>
                </div>

                <div class="form-control" id="current-storage">
                    <label>Current capacity:</label>
                    <input id="storage-capacity" disabled>
                </div>

                <div class="form-control" id="add-storage-amount">
                    <label for="additional-storage">Additional capacity (GB):</label>
                    <input type="number" id="additional-storage" name="additional-storage" step="1" required>
                </div>

                <button type="submit" class="save-storage-change">Save Changes</button>
            </div>
        </form>
    </div>
</div>
<script src="<c:url value='/assets/js/admin_addCapacity.js'/>"></script>
