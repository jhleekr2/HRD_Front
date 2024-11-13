<%@page import="dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
Member result = null;
if( request.getAttribute("result") != null) {
	result = (Member) request.getAttribute("result");
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%	if( result == null ) {%>

<h1>회원 가입 결과가 없습니다</h1>

<%	} else { %>

<h1>회원 가입 결과</h1>
<p>번호 : <%=result.getUserno() %></p>
<p>아이디 : <%=result.getUserid() %></p>
<p>별명 : <%=result.getNick() %></p>
<p>이메일 : <%=result.getEmail() %></p>

<%	} %>

<hr>

<a href="./join"><button>회원 가입 폼</button></a>

</body>
</html>