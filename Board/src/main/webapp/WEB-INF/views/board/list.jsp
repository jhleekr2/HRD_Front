<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
</head>
<body>

<h1>게시글 목록</h1>
<hr>

<table border="1">
<colgroup>
	<col style="width: 5%">
	<col style="width: 65%">
	<col style="width: 10%">
	<col style="width: 5%">
	<col style="width: 15%">
</colgroup>
<thead>
<tr>
	<th>글번호</th>
	<th>제목</th>
	<th>아이디</th>
	<th>조회수</th>
	<th>작성일</th>
	<!-- 닉네임, 추천수 추가 계획 -->
</tr>
</thead>

<tbody>
<c:forEach var="boardList" items="${boardList }">
<tr>
	<td>${boardList.boardno }</td>
	<td><a href="./view?boardno=${boardList.boardno }">${boardList.title }</a></td>
	<td>${boardList.userid }</td>
	<td>${boardList.hit }</td>
	<td>${boardList.writeDate }</td>
</tr>
</c:forEach>
</tbody>
</table>
</body>
</html>