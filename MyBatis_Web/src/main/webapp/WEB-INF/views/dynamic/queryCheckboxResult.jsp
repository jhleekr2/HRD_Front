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

<h1>동적쿼리 테스트</h1>
<h3>checkbox 조회 결과</h3>
<hr>

<a href="./query3"><button>부서 선택</button></a>

<hr>

<table border="1">
<tr>
	<th>사번</th>
	<th>이름</th>
	<th>부서</th>
</tr>

<c:forEach items="${list }" var="emp">
<tr>
	<td>${emp.EMPNO }</td>
	<td>${emp.ENAME }</td>
	<td>${emp.DEPTNO }</td>
</tr>
</c:forEach>
</table>

</body>
</html>