<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
	.outbox-container {
    margin: 0 auto;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
    table-layout: fixed;
}

td {
    padding: 12px;
    border-bottom: 1px solid #ddd;
    text-align: left;
    vertical-align: middle;
    font-size: 14px;
}

td:last-child {
    text-align: right;
    white-space: nowrap;
    font-weight: normal;
}

tr:hover {
    background-color: #f1f1f1;
    cursor: pointer;
}

td.subject-cell {
    width: 920px; 
    white-space: nowrap; 
    overflow: hidden; 
    text-overflow: ellipsis; 
}
</style>
<body>
	<link href="<c:url value='/assets/css/userHomePage_outbox.css'/>" rel="stylesheet">
	<div class="main">
	    <div class="outbox-container">
	        <table id="out-table">
	            <tbody id="outbox-body">
	            		<tr class="unread">	           
		            		<td>To</td>
			            	<td class="subject-cell" style="width: 900px">Topic</td>
			            	<td>Datetime</td>
		            	</tr>		            
	            </tbody>
	        </table>
	    </div>
	</div>
</body>
</html>