<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>액션 태그 테스트</h1>
<h3>include</h3>
<hr>

<%-- 외부 JSP 페이지 로드 --%>
<jsp:include page="./includepage.jsp" />
<%-- - action 태그 --%>

<%-- ** 한글이 ?로 나올 때 -> 데이터를 전송하는 쪽에 인코딩 설정해보기 --%>
<%-- ** 한글이 이상한 글자로 깨질 때 -> 데이터를 받는 쪽에 인코딩 설정해보기 --%>
<%-- 단, 위의 조치는 UTF-8이 latin1으로 깨질때의 조치이다 --%>

<% 	request.setCharacterEncoding("UTF-8"); %>
<%-- 한글 깨지는 문제 해결위해 추가 --%>

<jsp:include page="./includepage2.jsp">
	<jsp:param value="Hello!" name="data1"/>
	<jsp:param value="안녕하세요!" name="data2"/>
	<jsp:param value="HI! 여러 개 데이터!" name="data3">
</jsp:include>
<%-- jsp:include 태그는 중간에 아무것도 넣는 코드가 없다면 빈칸없이
끝내거나 그냥 바로 닫아버려야 한다 --%>

</body>
</html>