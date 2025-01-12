<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="../layout/header.jsp" />

<style type="text/css">
div.content {
	min-height: 300px;
}
</style>

<script type="text/javascript">
$(function() {
	$("#btnUpdate").click(() => location.href = "./update?boardno=${param.boardno}");
	$("#btnDelete").click(() => location.href = "./delete?boardno=${param.boardno}");
})
</script>
<div class="controller">
<h1>게시글 상세보기</h1>
<hr>

<table class="table table-bordered">

<tr>
<td class="table-info">글번호</td><td>${detail.boardno }</td>
<td class="table-info">작성일</td>
<td>
	<fmt:formatDate value="${detail.writeDate }" pattern="yyyy-MM-dd"/>
</td>
</tr>


<tr>
<td class="table-info">아이디</td><td>${detail.userid }</td>
<td class="table-info">닉네임</td><td>[ 추후 추가 ]</td>
</tr>

<tr>
<td class="table-info">조회수</td><td>${detail.hit }</td>
<td class="table-info">추천수</td><td>[ 추후 추가 ]</td>
</tr>

<tr>
<td class="table-info">제목</td><td colspan="3">${detail.title }</td>
</tr>

<tr>
<td colspan="4">
	<div class="content">${detail.content }</div>
</td>
</tr>

</table>

<div class="text-center">
<a href="./list"><button class="btn btn-primary">목록</button></a>
<button id="btnUpdate" class="btn btn-info">수정</button><!-- ./update?boardno=${viewBoard.boardno } -->
<button id="btnDelete" class="btn btn-danger">삭제</button><!-- ./delete?boardno=${param.boardno }-->
</div>
</div><!--  div.container -->

<c:import url="../layout/footer.jsp" />