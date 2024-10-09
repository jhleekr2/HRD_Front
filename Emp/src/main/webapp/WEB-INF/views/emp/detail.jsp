<%@page import="dto.Emp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% Emp emp = (Emp) request.getAttribute("result"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>사원 정보</h1>
<hr>

<p>사원번호 : <%=emp.getEmpno() %></p>
<p>사원이름 : <%=emp.getEname() %></p>
<p>직무 : <%=emp.getJob() %></p>
<p>담당자 : <%=emp.getMgr() %></p>

<p>입사날짜 : <%=emp.getHiredate() %></p>
<p>급여 : <%=emp.getSal() %></p>
<p>상여금 : <%=emp.getComm() %></p>
<p>부서번호 : <%=emp.getDeptno() %></p>

<a href = "./list"><button>사원 목록</button></a>
</body>
</html>