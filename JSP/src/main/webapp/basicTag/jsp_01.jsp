<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>Hello JSP!</h1>
<hr>

<!-- 경로 localhost:8085/ -> src/main/webapp/ -->

<% //스크립트릿 태그 영역 %>
<% //자바 코드를 작성하는 영역이다 %>
<% //자바 주석을 사용할 수 있다 %>
<% //JSP 자바 코드작성 단점 -> 이클립스에서 자동완성이 안된다 %>
<% //JSP코드는 동적 자원이고 Tomcat서버콘솔에서 실행된다 %>
<% //그런데 Tomcat 서버콘솔은 이클립스에 설치 -> Tomcat이 이클립스 콘솔에서 실행됨! %>
<% //클라이언트 콘솔 -> 브라우저 개발자도구 콘솔 %>
<% String name = "Alice"; %>
<% System.out.println("이름 : " + name); %>

<%------------------------------------------------------ --%>

<% for(int i=0; i<5; i++) { %>

	<h3>스크립트릿(Java) + HTML코드</h3>

<% } %>

<%----------------------------------------------------- --%>
<%-- 자바랑 HTML 코드를 섞어 써서 HTML에서 프로그래밍 구현 가능하다 --%>
<% //[워크스페이스]\.metadata\.plugins\org.eclipse.wst.server.core\tmp0 폴더에 톰캣 서버의 콘텐츠가 위치해 있다 %>

<% //JSP코드에서 적었던 내용들은 톰캣 서버에 의해 서블릿으로 자동으로 변환되고
//그 내용들은 jasper폴더에 저장된다. 그런데, 서블릿으로 변환되는 과정 특성상
//스크립트릿에서의 자바 코드 사용에 몇가지 제한이 생긴다. %>

<% //접근제한자를 적용할 수 없다
// private int num = 100;

   //메소드를 정의할 수 없다
// public void method() {}

   //import 코드를 작성할 수 없다
// import java.util.List();

%>

<% //접근제한자, 메소드 정의는 선언태그에서 할 수 있다 %>
</body>
</html>