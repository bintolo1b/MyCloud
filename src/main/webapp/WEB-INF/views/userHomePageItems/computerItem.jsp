<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Computer Page</title>
</head>
<body>
    <h1>This is the Computer Section</h1>

    <%
        String message = "Welcome to the Computer Section!";
        String[] files = {"file1.txt", "file2.docx", "file3.pptx"};
        int fileCount = files.length;
    %>

    <p><%= message %></p>

    <p>There are <%= fileCount %> files available:</p>

    <ul>
        <% for (int i = 0; i < files.length; i++) { %>
            <li><%= files[i] %></li>
        <% } %>
    </ul>

    <p>
        <% 
            if (fileCount > 0) {
                out.println("There are files to download.");
            } else {
                out.println("No files available.");
            }
        %>
    </p>
    
    
</body>
</html>
