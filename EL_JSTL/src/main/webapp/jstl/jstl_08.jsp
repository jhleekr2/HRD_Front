<%@page import="java.util.Date"%>
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

<h1>fmt:formatDate</h1>
<hr>

<%-- 날짜는 Java로도, JSTL로도 형식지정을 할 줄 알아야 한다 --%>
<%	Date date = new Date(); %>
<%=date %>

<c:set var="now" value="<%=new Date() %>"/>
<hr>

기본 : <fmt:formatDate value="${now }"/><br>
<%-- 기본값은 date --%>
date : <fmt:formatDate value="${now }" type="date"/><br>
time : <fmt:formatDate value="${now }" type="time"/><br>
both : <fmt:formatDate value="${now }" type="both"/><br>

<hr>

<h3>type="date"</h3>

default : <fmt:formatDate value="${now }" type="date" dateStyle="default"/><br>
<%-- 기본값은 medium --%>
short : <fmt:formatDate value="${now }" type="date" dateStyle="short"/><br>
medium : <fmt:formatDate value="${now }" type="date" dateStyle="medium"/><br>
long : <fmt:formatDate value="${now }" type="date" dateStyle="long"/><br>
full : <fmt:formatDate value="${now }" type="date" dateStyle="full"/><br>

<hr>

<h3>type="time"</h3>

default : <fmt:formatDate value="${now }" type="time" timeStyle="default"/><br>
<%-- 기본값은 medium --%>
short : <fmt:formatDate value="${now }" type="time" timeStyle="short"/><br>
medium : <fmt:formatDate value="${now }" type="time" timeStyle="medium"/><br>
long : <fmt:formatDate value="${now }" type="time" timeStyle="long"/><br>
full : <fmt:formatDate value="${now }" type="time" timeStyle="full"/><br>

<hr>

<h3>type="both"</h3>

<fmt:formatDate value="${now }" type="both"
 dateStyle="full" timeStyle="short"/>
 
<hr>

<h3>패턴 지정</h3>
<%-- 자바의 NumberFormat에 지정된 형식을 따른다 --%>
<fmt:formatDate value="${now }" pattern="yyyy/MM/dd a HH:mm:ss.S" />

<%-- MM - 월 (대문자 M) --%>
<%-- mm - 분 (소문자 m) --%>

<%-- HH - 24시간기준 시 (대문자 H, 자정이 00시) --%>
<%-- hh - 12시간기준 시 (소문자 h, 자정이 12시) --%>

<%-- kk - 24시간기준 시 (소문자 k, 자정이 24시) --%>
<%-- KK - 12시간기준 시 (대문자 K, 자정이 00시) --%>

<%-- 일상에서는 HH기준이 더 많이 사용된다 --%>

<hr>

<%-- 타임존 관련 --%>

<fmt:formatDate value="${now }" pattern="z" /><br>
<fmt:formatDate value="${now }" pattern="Z" /><br>
<fmt:formatDate value="${now }" pattern="X" /><br>
</body>
</html>