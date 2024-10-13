<%@page import="dto.User" %>
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
<hr>

<%-- ** 스크립트릿을 이용하여 자바 객체를 생성하고 콘텍스트 정보로 등록 --%>
<%	User user1 = new User(); //자바 객체 생성
// User는 지역 변수라서 private, public 등을 쓸 수 없다
	pageContext.setAttribute("user1", user1); //page콘텍스트 정보 등록

%>
<%-- ** 액션태그를 이용하여 자바 객체를 생성하고 콘텍스트 정보로 등록 --%>
<jsp:useBean id="user2" class="dto.User"/>
<%-- 위 자바코드와 같은 역할 수행 --%>
<%-- JSP 만들때는 가독성 문제 때문에 자바코드를 되도록 안 쓰는 것이 좋다 --%>

<%-- 콘택스트 정보로 등록된 자바 객체를 자바 빈(Java Bean)이라고 한다 --%>

user1: <%=user1 %><br>
user2: <%=user2 %><br>

<%------------------------------------------------------------------- --%>
<hr>

<%-- ** useBean 액션태그로 등록된 컨텍스트 정보도 
	자바 코드로 접근할 수 있다 --%>

<%	user1.setUserid("id1");
	user1.setUserpw("pw1");
	
	user2.setUserid("id2");
	user2.setUserpw("pw2");
%>

user1: <%=user1 %><br>
user2: <%=user2 %><br>

<%------------------------------------------------------------------- --%>
<hr>

<jsp:useBean id="user3" class="dto.User" />

<jsp:setProperty property="userid" name="user3" value="id3"/>
<%-- user3.setUserid("id3");과 같은 뜻 --%>
<jsp:setProperty property="userpw" name="user3" value="pw3"/>

user3: <%=user3 %><br>

user3.userid : <jsp:getProperty property="userid" name="user3"/><br>
user3.userpw : <jsp:getProperty property="userpw" name="user3"/><br>

<%-- JSP에서의 에러는 7줄 표시되는 코드 중 가운데 부분이 에러가 발생되는 곳이다 --%>

<%------------------------------------------------------------------- --%>
<hr>

<%	User user4 = new User(); %>

<%-- **에러 발생 : 서블릿으로 변환하던 중에 NullPointerException이 발생 --%>
<%-- <jsp:setProperty property="userid" name="user4" value="id4"/> --%>

<%-- 문제 해결하려면 서블릿 변환결과(일종의 자바코드)를 봐야만 해결할 수 있다 --%>
<%-- .의 왼쪽이 null이기 때문에 NullPointerException 발생 --%>
<%-- 그냥 user4.로 꺼내는 것이 아니라 형변환까지 해서--%>
<%--((User)pageContext.getAttribute("user4")).setUserid("id4"); 를 서블릿변환하고 실행하다 에러 --%>
<%-- 여기서는 ((User)pageContext.getAttribute("user4")) == null 이라서 에러 발생 --%>
<%-- 해결법 - jsp page scope <- 자바 scope 등록 --%>
<%	pageContext.setAttribute("user4", user4);
	request.setAttribute("user4", user4);
	session.setAttribute("user4", user4);
	application.setAttribute("user4", user4);
%>

<jsp:setProperty property="userpw" name="user4" value="pw4"/>

<%-- 스코프가 없을때는 알아서 영역 넓혀가면서 탐색할 것이다 --%>
user4: <%=user4 %>
</body>
</html>