<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% 	request.setCharacterEncoding("UTF-8"); %>
<%-- 한글 깨지는 문제 해결위해 추가 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>EL을 이용한 전달 파라미터 처리</h1>
<hr>

<a href="./el_06.jsp">테스트 폼</a>
<%-- 링크를 걸어서 테스트 페이지로 이동한다는 발상이 필요하다. --%>
<hr>

<h3>param</h3>

test : ${param.test }<br>
data : ${param.data }

<hr>

<h3>paramValues</h3>

genre의 첫 번째 선택 : ${paramValues.genre[0] }<br>
genre의 두 번째 선택 : ${paramValues.genre[1] }<br>
genre의 세 번째 선택 : ${paramValues.genre[2] }

<hr>

<%-- ** EL의 출력값은 HTML 문서의 어디에든 적용할 수 있다 --%>
<%-- JS코드, CSS코드, HTML속성, 등등 --%>
<div id="${param.test }">

</div>
</body>
</html>