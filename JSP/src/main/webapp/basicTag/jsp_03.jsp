<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>
<%--	<% response.getWriter().write("자바 데이터"); %> --%>
	<%-- response.getWriter().write();를 전부 생략하여 = 로 바꾼다 --%>
	<%-- -> 표현식에서는 둥근괄호와 ;를 적지 않는다. --%>
	<%="자바 데이터2" %>
</h1>

<h1>
	<% out.print("자바 데이터3"); %>

<%-- 표현식 태그를 이용하여 자바 데이터를 화면에 출력할 수 있다 --%>
<%-- 스크립트릿 태그에서 out 키워드를 이용 자바 데이터를 화면에 출력한다 --%>

</h1>
</body>
</html>