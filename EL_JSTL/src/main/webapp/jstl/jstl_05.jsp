<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>c:forTokens</h1>
<hr>

<%	String names ="Adore Bat Cpp Dave";

	//String[] name = names.split(" ");
%>
<%-- 자바에서는 .split()으로 관리했지만, JSTL의 forTokens로 --%>
<%-- 관리하면, 알아서 관리해주고 반복수행까지 자동으로 수행된다! --%>

<%-- names 문자열을 delims로 split()하고 순차적으로 name변수에 저장 --%>

<c:forTokens items="<%=names %>" delims=" " var="name">
	${name }<br>
</c:forTokens>

<hr>

<%	String dates="0416:0529:0807:1124:1225"; %>

<c:forTokens items="<%=dates %>" delims=":" var="d">
	${d }<br>
</c:forTokens>

</body>
</html>