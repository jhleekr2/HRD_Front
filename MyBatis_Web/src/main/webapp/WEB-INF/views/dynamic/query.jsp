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

<h1>해시맵을 이용한 조회결과</h1>
<hr>

<c:forEach items="${list }" var="emp">
<div>
사원번호 : ${emp.EMPNO }, 사원이름 : ${emp.ENAME }, 부서번호 : ${emp.DEPTNO }
</div>
</c:forEach>

</body>
</html>