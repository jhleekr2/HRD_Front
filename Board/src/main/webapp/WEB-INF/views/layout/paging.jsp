<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>

<ul class="pagination justify-content-center">
	
	<%-- 첫 페이지로 이동 --%>
	<c:if test="${paging.curPage ne 1 }">
	<li class="page-item">
		<a class="page-link" href="./list">&larr; 처음</a>
	</li>
	</c:if>

	<%-- 이전 페이징 리스트로 이동 --%>
	<c:if test="${paging.startPage ne 1 }">
	<li class="page-item">
		<a class="page-link" href="./list?curPage=${paging.startPage - paging.pageCount }">&laquo;</a>
	</li>
	</c:if>
	
	<c:forEach var="i" begin="${paging.startPage }" end="${paging.endPage }">
		<%-- 페이지 표시 과정에서 jsp에 의외로 자바 코드가 많이 들어가는데, 자바 코드가 너무 많으면 가독성이 확 떨어진다 --%>
		<%-- 때문에, 떨어지는 가독성을 보완하기 위해 JSTL같은 것들을 쓰면서 보완한다 --%>
		<%-- 페이징 번호 리스트 --%>
		<c:if test="${paging.curPage eq i }">
			<li class="page-item active">
				<a class="page-link" href="./list?curPage=${i }">${i }</a>
			</li>
		</c:if>
		<c:if test="${paging.curPage ne i }">
  			<li class="page-item">
  				<a class="page-link" href="./list?curPage=${i }">${i }</a>
  			</li>
  		</c:if>
  	</c:forEach>
  	
  	<%-- 다음 페이징 리스트로 이동 --%>
	<c:if test="${paging.endPage ne paging.totalPage }">
	<li class="page-item">
		<a class="page-link" href="./list?curPage=${paging.startPage + paging.pageCount }">&raquo;</a>
	</li>
	</c:if>
	
	<%-- 마지막 페이지로 이동 --%>
	<%-- 최근에는 마지막 페이지 구현 안하는 경우도 많다 --%>
	<c:if test="${paging.curPage ne paging.totalPage }">
	<li class="page-item">
		<a class="page-link" href="./list?curPage=${paging.totalPage }">&rarr; 마지막</a>
	</li>
	</c:if>
	
</ul>

</div>