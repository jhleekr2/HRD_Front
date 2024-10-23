<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>EL 테스트</h1>
<hr>

<%="<h3>표현식 태그</h3>" %>
<h3><%="표현식 태그" %></h3>
<%-- JSP는 서버측 코드이며, 아래쪽 스타일을 더 권장 --%>

<hr>

${"<h3>표현 언어, EL</h3>" }
<h3>${"표현 언어, EL" }</h3>
<%-- 이클립스가 EL언어 인식하는데 버그가 있어서 거짓 오류를 종종 보고 --%>
<%-- 창을 닫거나 다시 조작해서 거짓오류 없애는 것을 시도하며 --%>
<%-- 아래쪽 스타일의 코드 작성을 강력히 권장 --%>

<hr>

<%	out.print("<h3>스크립트릿</h3>"); %>
<h3><%	out.print("스크립트릿"); %></h3>
<%-- JSP의 기본 문법은 스크립트릿으로 왠만한건 다 가능, 하지만, 좀 복잡하고 쓰기 불편 --%>
<%-- 좀더 간소화된 EL 사용 --%>

<%-- 개발자가 프론트엔드 배우는 이유 - 퍼블리셔와의 협업 목적! --%>
</body>
</html>