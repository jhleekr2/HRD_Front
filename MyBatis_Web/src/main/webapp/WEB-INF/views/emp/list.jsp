<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	width: 1100px;
	margin: 0 auto;
}

table, th, td {
	border: 1px solid #ccc;
	border-collapse: collapse;
}

th, td {
	padding: 5px;
}
</style>
</head>
<body>

<h1>사원 목록</h1>
<hr>

<!-- controller의 setAttribute의 왼쪽에 있는 것의 이름을 EL에 적는다 -->
<%-- ${list } --%>
<!-- ArrayList의 toString()형태가 출력 -> 좀더 예쁘게 출력하려 노력해야 한다 -> JSTL 사용 -->

<table>
<colgroup>
	<col style="width: 5%">
	<col style="width: 20%">
	<col style="width: 15%">
	<col style="width: 5%">

	<col style="width: 15%">
	<col style="width: 15%">
	<col style="width: 15%">
	<col style="width: 10%">
</colgroup>

<thead>
<tr>
	<th>사번</th>
	<th>사원이름</th>
	<th>직무</th>
	<th>담당</th>

	<th>입사날짜</th>
	<th>급여</th>
	<th>상여금</th>
	<th>부서</th>
</tr>
</thead>

<tbody>
<c:forEach var="emp" items="${list }">
<tr>
	<td>${emp.empno }</td>
	<td>
		<a href="./detail?empno=${emp.empno }">
		<!-- emp/detail로 갈 것이다 -->
			${emp.ename }
		</a>
	</td>
	<td>${emp.job }</td>
	<td>${emp.mgr }</td>
	<td>
		<fmt:formatDate value="${emp.hiredate }" pattern="yyyy-MM-dd"/>
	</td>
	<td>${emp.sal }</td>
	<td>${emp.comm }</td>
	<td>${emp.deptno }</td>
</tr>
</c:forEach>
</tbody>

</table>
</body>
</html>