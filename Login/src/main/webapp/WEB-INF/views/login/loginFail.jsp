<%@ page import="java.util.Date" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1 style="color: red;">로그인 실패</h1>
<hr>

<h3>아이디와 패스워드를 확인하고, 다시 시도하세요</h3>
세션 아이디 : <%=session.getId() %><br>
세션을 생성한 시간 : <%=new Date(session.getCreationTime()) %><br>
최근(마지막) 접속한 시간 : <%=new Date(session.getLastAccessedTime()) %>

<!-- <a href="./login">메인 화면</a> -->
<a href="/">메인 화면</a>
</body>
</html>