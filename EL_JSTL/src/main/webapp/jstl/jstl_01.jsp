<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- JSTL 태그 활성화 지시자 --%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- c를 가장 많이 쓸것이고 fmt도 가끔 쓰일 것이다 나머지는 거의 안쓰일 것이다 --%>

<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>c:set</h1>
<hr>

<%-- page 컨텍스트 영역에 data=DATA3 이라는 정보를 등록한다 --%>
<c:set var="data" value="DATA3"/>
<%-- 스코프를 지정하지 않으면 기본적으로 페이지 영역에 저장 --%>
data : ${data }

<%-- session 컨텍스트 영역에 정보 등록하기 --%>
<c:set var="sessionData" value="SESSION123" scope="session"/>
<%-- URL이 바뀌어도 session 영역의 데이터는 유지된다 --%>
<hr>

<a href="./jstl_01_session.jsp">세션 확인</a>
<hr>
</body>
</html>