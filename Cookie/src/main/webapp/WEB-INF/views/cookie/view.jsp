<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>쿠키 확인</h1>
<hr>

<table border="1">
<tr>
	<th>NAME</th>
	<th>VALUE</th>
</tr>
<c:forEach items="${cookie }" var="c">
<%-- Map<String, Cookie> --%>
<%-- <td>${c.key }</td> --%>
<tr>
	<td>${c.key }</td>
	<td>${c.value.value }</td>
<%-- 	<td>${c.value.getValue() }</td> --%>
</c:forEach>
</table>

<hr>

<h3><a href="./create">쿠키 생성</a></h3>
<h3><a href="./update">쿠키 수정</a></h3>
<h3><a href="./delete">쿠키 삭제</a></h3>
</body>
</html>