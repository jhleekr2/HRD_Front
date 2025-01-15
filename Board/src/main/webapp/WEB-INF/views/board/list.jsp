<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="../layout/header.jsp" />

<!-- script 한칸 띄우고 Ctrl+space 누르면 자동으로 type="text/javascript" 입력이 된다 -->
<!-- 최근에는 type이하 부분을 생략하기도 한다 -->
<script type="text/javascript">
$(function() {
	$("#btnWrite").click(function() {
		location.href = "./write";
	});
})
</script>

<div class="container">
<h1>게시글 목록</h1>
<hr>

<table class="table table-striped table-hover table-sm text-center">
<colgroup>
	<col style="width: 5%">
	<col style="width: 65%">
	<col style="width: 10%">
	<col style="width: 5%">
	<col style="width: 15%">
</colgroup>
<thead>
<tr class="table-primary">
	<th>글번호</th>
	<th>제목</th>
	<th>아이디</th>
	<th>조회수</th>
	<th>작성일</th>
	<!-- 닉네임, 추천수 추가 계획 -->
	<!-- bootstrap에서는 col을 12등분했다고 생각하고 col-(크기) 입력하면 자동으로 12등분의 (크기)만큼으로 간격조정 -->
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

<div class="float-end">
<button id="btnWrite" class="btn btn-primary">글쓰기</button>
</div>
<c:import url="../layout/paging.jsp" />
</div><!-- div.container -->

<c:import url="../layout/footer.jsp" />