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
<h1>c:choose</h1>
<h3>전달파라미터 choose 페이지</h3>
<hr>

<%-- 전달 파라미터 sel 출력 --%>
<%-- ${param.sel } --%>

<c:choose>
	<c:when test="${param.sel eq 1 }">
	<p>1 을 선택하셨습니다</p>
	</c:when>
	
	<c:when test="${param.sel eq 2 }">
	<p>2 을 선택하셨습니다</p>
	</c:when>
	
	<c:when test="${param.sel eq 3 }">
	<p>3 을 선택하셨습니다</p>
	</c:when>
	
	<c:otherwise>
	<p>1, 2, 3 중 하나를 선택하세요</p>
	</c:otherwise>
</c:choose>

<h3><a href="./jstl_03.jsp"><button>선택 화면</button></a></h3>

</body>
</html>