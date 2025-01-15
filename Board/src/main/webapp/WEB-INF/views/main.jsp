<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="./layout/header.jsp"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>메인 화면</h1>
<hr>

로그인 상태 : ${login }<br>
비로그인 상태 : ${empty login }

<%-- 비로그인 상태 --%>
<c:if test="${empty login }">

<a href="/member/login"><button class="btn btn-primary">로그인</button></a>
<a href="/member/join"><button class="btn btn-success">회원가입</button></a>

</c:if>

<%-- 로그인 상태 --%>
<c:if test ="${not empty login and login eq true }">

<a href="/board/list" class="btn btn-info">게시판 목록</a>
<a href="/member/logout" class="btn btn-warning">로그아웃</a>

</c:if>
<hr>

<c:import url="./layout/footer.jsp"/>
