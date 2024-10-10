<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 톰캣에 알려주는 부분 --%>
<%@ page errorPage="./errorpage.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 브라우저에 알려주는 부분 -->
<title>Insert title here</title>
</head>
<body>

<h1>에러 테스트 페이지</h1>
<hr>

<%	try {
		int num = 7 / 0;
	} catch( ArithmeticException e ) {
//		e.printStackTrace();
%>
		<h3>예외 발생</h3>
		<h6>0으로 나눌 수 없습니다</h6>
		<!-- JSP에서는 서비스 로직을 되도록 안쓸것이고 -->
		<!-- 그 결과 JSP에서 예외처리할 일은 없을 것이다 -->
<%	
	}
%>

<!-- JSP 안에서의 오류는 브라우저에서 상세하게 보여준다 -->
<!-- HTTP 500 오류는 서버 콘솔에 뜰 것이다 -->
<%----------------------------------------------------- --%>

<%	//NumberFormatException 발생
	Integer.parseInt("ABCDE");
%>

</body>
</html>