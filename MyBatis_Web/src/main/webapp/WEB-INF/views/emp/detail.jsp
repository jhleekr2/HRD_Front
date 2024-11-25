<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
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
<!-- body안에 있는 모든 요소들은 무조건 div로 감싸주는 것이 맞다. -->
<!-- 나중에 디자인을 편집하는데 유리하기 때문 -->
<!-- 화면도 사실 팀프로젝트 때에 미리 회의 때부터 구상을 해서 넣는것이 맞다 -->
<!-- 뭔가 화면에 하나 만들때마다 일일히 CSS줘야 하기 때문에 계획 없으면 계속 어긋날 것이다 -->
<div id="container">

<div class="title">
	<h1>사원 상세보기</h1>
	<hr>
</div>

<div class="detail">
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
<tr>
<!-- EL은 getter를 안해도되도록 바꿔주는 기능이 있다 -> 그래서 편리하다 -->
	<td>${detail.empno }</td>
	<td>${detail.ename }</td>
	<td>${detail.job }</td>
	<td>${detail.mgr }</td>
	<td>
		<fmt:formatDate value="${detail.hiredate }" pattern="yyyy-MM-dd"/>
	</td>
	<td>${detail.sal }</td>
	<td>${detail.comm }</td>
	<td>${detail.deptno }</td>
</tr>

</tbody>
</table>
</div>

<div>
	<a href="./list"><button>이전으로</button></a><br>
</div>
</div>
</body>
</html>