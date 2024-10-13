<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3>포워딩된 페이지</h3>

<h5>첫 요청 URL의 화면이 변경된다(forward)</h5>
<h5>URL(주소)은 변경되지 않는다</h5>

<hr>

<p>data : <%=request.getAttribute("data") %></p> <%-- request.setAttribute --%>
<p>data : <%=request.getParameter("data") %></p> <%-- jsp:param --%>
</body>
</html>