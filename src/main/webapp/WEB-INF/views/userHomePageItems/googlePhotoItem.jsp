<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>This is GooglePhoto</h1>
   <p>1</p>
   <script type="text/javascript">
   (function() {
	   let count = 1; // Khởi tạo biến đếm
		
	   setInterval(function() {
	      // Tăng biến đếm mỗi giây
	       count++;
	      // Truy vấn thẻ <p> và gán giá trị của count vào nội dung
	      document.querySelector("p").textContent = count; 
	   }, 1000); // Mỗi 1 giây (1000 ms)	
   })();
   
   </script>
</body>

</html>