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

<h1>c:if</h1>
<hr>

<!-- 자동완성이 안뜨면 사전에 활성화가 안됐는지 체크 필요! -->
<c:if test="true">
<h3>TRUE: 무조건 실행</h3>
</c:if>

<hr>

<c:if test="false">
<h3>FALSE: 무조건 실행하지 않음</h3>
</c:if>

<hr>

<%-- test속성값이 "true"일 때만 수행된다 --%>
<c:if test="문자열">
<h3>출력여부?</h3><!-- 출력 X -->
</c:if>

<hr>

<%-- 출력되지 않는 상황 --%>
<c:if test="${13 eq 13 }">
<h3>출력!!!!</h3>
</c:if>

<%-- ** 문제가 되는 상황 --%>
<%-- -> 속성값에 " " 띄어쓰기를 추가해서 --%>
<%-- "true"가 아닌 "true " 가 test속성에 적용된다 --%>
test속성값 : "${13 eq 13 } "
<%-- 태그와 만나서 논리적 설계를 할 수 있도록 한다 --%>

<hr>

<c:if test="${empty boardList }">
	<%-- 게시글 조회 결과가 없을 경우 --%>
	<h3>게시글이 없습니다
</c:if>

<c:if test="${not empty boardList }">
	<%-- 게시글 조회 결과가 있을 경우 --%>

	<table>
	<tr>
		<td>조회 결과 보여주기</td>;
	</tr>
	</table>
	

</c:if>
</body>
</html>