<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Hello!</h1>

<h3>전달 데이터 : <%=request.getParameter("data") %></h3>

<h3>컨텍스트 정보 : <%=request.getAttribute("contextData") %></h3>
<!-- 컨트롤러의 req객체 == JSP의 request객체 -->
<!-- 포워딩을 통해 전달하는 데이터와 각종 속성들이 연결된다 -->
<!-- 전달 데이터와 컨텍스트 정보 구별이 중요하다! -->
</body>
</html>