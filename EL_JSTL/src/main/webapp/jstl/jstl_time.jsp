<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- 자바에서 날짜/시간 표현 클래스 --%>

<%-- * java.util.Date --%>
<%-- 고정된 날짜/시간 표현 --%>
<%-- 날짜/시간 변경 안함 --%>
<%-- 상수 시간 --%>

<%-- * java.util.Calendar --%>
<%-- 날짜/시간 표현(변경) 가능 --%>

<%-- * java.util.GregorianCalender --%>
<%-- Calendar의 하위 클래스 --%>
<%-- 그레고리력(양력)을 표현하기 적합함 --%>
<%-- 날짜/시간 데이터를 직접 입력해서 생성할 수 있다 --%>

<%----------------------------------------------------------- --%>

<%-- GregorianCalendar의 경우 1월이 0부터 시작함 --%>
<%-- 따라서, 9는 10월을 나타냄 --%>
<% GregorianCalendar calendar = new GregorianCalendar(2024, 9, 12, 11, 12, 21); %>

<%-- 날짜/시간 서식의 출력 지정되어 있지 않다(toString 형태로 나온다) --%>
<%=calendar %>
<hr>

<%-- ** java.util.Date 타입으로 반환한다 --%>
<%=calendar.getTime() %>

<hr>
<%----------------------------------------------------------- --%>

<%-- 현재시간을 기준시간으로 생각하고 시간1, 시간2를 지정하여 생성 --%>

<%--   시간1 : 2024-10-23 14:51:27 --%>
<%--   시간2 : 2024-10-26 10:15:42 --%>

<%--   JSTL을 이용하여 현재시간과 비교하여 시간1, 시간2를 출력한다 --%>
<%--   기준날짜보다 이전 날짜라면 연-월-일 로 출력 --%>
<%--   기준날짜와 같은 날짜라면 시:분 으로 출력 --%>

<%--    c:if, c:choose, c:when, c:set, fmt:formatDate 등을 활용한다 --%>

<%-- 현재 날짜 --%>
<%-- -> var="now"가 적용되면 출력하지 않고 now 변수에 저장한다 --%>
<fmt:formatDate var="now" value="<%=new Date() %>" pattern="yyyyMMdd"/>
현재 날짜: ${now }

<%-- 시간1 : 2024-10-23 14:51:27 --%>
<c:set var="time1"
 value="<%=new GregorianCalendar(2024, 9, 23, 14, 51, 27).getTime() %>"/>
 
<%-- 시간2 : 2024-10-26 10:15:42 --%>
<c:set var="time2"
 value="<%=new GregorianCalendar(2024, 9, 26, 10, 15, 42).getTime() %>"/>

time1 : ${time1 }<br>
time2 : ${time2 }<br>

<hr>

<fmt:formatDate value="${time1 }" pattern="yyyyMMdd" var="time1str"/>
<fmt:formatDate value="${time2 }" pattern="yyyyMMdd" var="time2str"/>

time1str : ${time1str }<br>
time2str : ${time2str }<br>

<hr>

time1:
<c:choose>
	<c:when test="${time1str lt now }">
		<fmt:formatDate value="${time1 }" pattern="yyyy-MM-dd"/>
	</c:when>
	<c:when test="${time1str ge now }">
		<fmt:formatDate value="${time1 }" pattern="HH:mm"/>
	</c:when>
</c:choose>
<br>

time2:
<c:choose>
	<c:when test="${time2str lt now }">
		<fmt:formatDate value="${time1 }" pattern="yyyy-MM-dd"/>
	</c:when>
	<c:when test="${time2str ge now }">
		<fmt:formatDate value="${time2 }" pattern="HH:mm"/>
	</c:when>
</c:choose>
</body>
</html>