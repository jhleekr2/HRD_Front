<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>액션태그 테스트</h1>
<h3>forward</h3>
<hr>

<%-- <%	request.setAttribute("data", "Apple"); %> --%>
<%-- <jsp:forward page="./forwarding.jsp" /> --%>

<%-- 데이터 추가는 forwarding하기 전에 해야만 데이터가 정상적으로 넘어간다 --%>
<%-- 그렇지 않으면 넘어간 쪽에서 데이터가 null로 나온다 --%>

<%---------------------------------------------------------------------- --%>

<jsp:forward page="./forwarding.jsp">
	<jsp:param value="Banana" name="data"/>
</jsp:forward>
</body>
</html>