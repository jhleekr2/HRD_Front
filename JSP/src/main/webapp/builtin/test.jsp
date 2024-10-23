<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>내장 객체 테스트</h1>
<hr>

<h3>Request 객체</h3>

<%-- 요청 정보 객체 : <%=request %> --%>

현재 URL : <%=request.getRequestURI() %><br>
요청 Method : <%=request.getMethod() %><br>
요청 Parameter : <%=request.getParameter("hello") %><br>

<%-- 요청 Parameter : <%=request.getParameter("name") %> --%>
<%-- JSP도 서블릿이 하던 기능들을 똑같이 할 수 있다 --%>
<%-- http://localhost:8088/builtin/test.jsp?hello=hihi 했을때 hihi가 뜰 것이다 --%>
<%-- hello=hihi부분 -> 쿼리 스트링(Query String)이라고 부른다 --%>
<%-- 쿼리 스트링의 형식 : name=value 형태 --%>

<%---------------------------------------------------------------------------- --%>

<%-- 요청 파라미터 테스트 --%>

<%-- 테스트 URL --%>
<%-- http://localhost:8085/builtin/test.jsp?param=하이!&data=Apple&data=Banana --%>
param 요청 파라미터 : <%=request.getParameter("param") %><br>

<%-- 같은 이름의 파라미터가 여러 개여도 1개만 추출한다 --%>
<%-- data 요청 파라미터 : <%=request.getParameter("data") %><br> --%>

<%---------------------------------------------------------------------------- --%>
<hr>

<%-- ** 같은 이름의 파라미터가 여러개일 때
	-> request.getParameterValues("name") 을 이용한다 --%>
	
<%-- <% String[] datas = request.getParameterValues("data"); %> --%>

data 요청 파라미터 : <%=request.getParameterValues("data")[0] %><br>
data 요청 파라미터 : <%=request.getParameterValues("data")[1] %><br>

<%-- 요청 파라미터를 1개 이하로 적거나 아예 적지 않으면 에러가 발생한다 --%>
<%---------------------------------------------------------------------------- --%>
<h3>Response 객체</h3>

응답 데이터의 인코딩 확인 : <%=response.getCharacterEncoding() %><br>

응답 데이터의 형식 : <%=response.getContentType() %>
<%---------------------------------------------------------------------------- --%>
<hr>

<h3>Out 객체</h3>

<%-- <% response.getWriter(); %> 와 같음 --%>

<%	out.append("Hello")
		.append("<p>응답으로 사용할 내용 작성하기</p>")
		.append("<hr>")
		.append("<%= %&gt; 표현식 태그를 이용한 것과 같다");
%>

<%---------------------------------------------------------------------------- --%>
<hr>

<h3>Page 객체</h3>

<%=page %><br>
<%=this %>

<%---------------------------------------------------------------------------- --%>
<hr>

<h3>Config 객체</h3>

<%=config.getServletName() %>
<%---------------------------------------------------------------------------- --%>
<hr>

<h3>Context 정보 객체</h3>

<%	//page
	pageContext.setAttribute("name", null); //값 저장
	pageContext.getAttribute("name"); //값 불러오기

	//request
	request.setAttribute("name", null);
	request.getAttribute("name");
	
	//session
	session.setAttribute("name", null);
	session.getAttribute("name");
	
	//application
	application.setAttribute("name", null);
	application.getAttribute("name");

%>

</body>
</html>