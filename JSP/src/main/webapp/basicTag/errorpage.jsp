<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 style="color: red;">에러 처리 페이지</h1>
<hr>
<h3>에러 상황</h3>
<%=exception %>
<!-- java의 catch역할 한다 -->
</body>
</html>