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

<h1>세션 확인</h1>
<hr>
<%-- JSP에서는 세션에 대한 내장 객체가 이미 존재해서 좀더 편하게 확인할 수 있다 --%>
세션 아이디 : <%=session.getId() %><br>
세션을 생성한 시간 : <%=new Date(session.getCreationTime()) %><br>
최근(마지막) 접속한 시간 : <%=new Date(session.getLastAccessedTime()) %>

<hr>

test 컨텍스트 정보 : <%=session.getAttribute("test") %><br>
test 컨텍스트 정보 : ${test }

<h3><a href="./create">세션 생성</a></h3>
<h3><a href="./delete">세션 삭제</a></h3>
</body>
</html>