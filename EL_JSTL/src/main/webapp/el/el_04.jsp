<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>EL의 컨텍스트 정보 객체</h1>
<hr>

<%	int localData = 100; //자바 영역 변수 %>
<%-- EL은 자바 영역 변수를 인식하지 못한다 --%>
localData : ${localData }

<hr>

<%	//컨텍스트 영역의 정보 등록하기
	pageContext.setAttribute("pageData", 200);
	request.setAttribute("requestData", 300);
	session.setAttribute("sessionData", 400);
	application.setAttribute("applicationData", 500);
%>

<hr>
<%-- EL은 컨텍스트 정보 곧바로 꺼내기에 장점이 있다 --%>

page : ${pageScope.pageData }<br>
page : ${pageScope["pageData"] }<br>

<%-- EL의 문법은 자바보다는 자바스크립트의 특성을 갖고 있다 --%>

request : ${requestScope.requestData }<br>
session : ${sessionScope.sessionData }<br>
application : ${applicationScope.applicationData }

<%-- ** EL에서는 이 4가지 객체가 많이 사용되는데, 그중에서도 request와 session이 주로 사용될 것이다 --%>

<hr>
page : ${pageData }<br>
request : ${requestData }<br>
session : ${sessionData }<br>
application : ${applicationData }

<%-- EL에서는 변수 이름만 넣어도 알아서 컨텍스트 정보를 꺼낼 수 있다. --%>
<%-- -> EL에서는 page변수부터 request, session, application변수 순으로 알아서 변수를 찾기 때문 --%>

<%-- ** 컨텍스트 정보 객체를 생략해서 변수만 사용할 수 있다 --%>

</body>
</html>