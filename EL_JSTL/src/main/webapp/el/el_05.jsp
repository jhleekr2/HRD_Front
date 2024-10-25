<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- pageContext 내장 객체 --%>

${pageContext }<br>
${pageContext.request }<br>
<%=request %><br>

%{pageContext.request.requestURI }

<%=pageContext.getRequest() %><br>

<%=((HttpServletRequest)pageContext.getRequest()).getRequestURI() %><br>
<%-- EL을 안쓰면 형변환도 해야하고 사용하기가 상당히 불편하다 --%>

<hr>

<%-- 전달 파라미터 --%>

${param }<br>
<%-- ?data1=Apple을 전달 파라미터로 추가하면 전달 파라미터가 출력된다 --%>

${param.data1 }<br>
${param.data2 }<br><br>

${paramValues }<br>
${paramValues.data1[0] }<br><br>
<%-- http://localhost:8085/el/el_05.jsp?data1=Apple&data2=Banana&hobby=music&hobby=movie&hobby=book 주소 접속 --%>

${paramValues.hobby[0] }<br>
${paramValues.hobby[1] }<br>
${paramValues.hobby[2] }<br>

<hr>

<%-- 요청 헤더 정보 --%>

${header }<br><br>

${header.host }<br>

<%-- ${header.user-agent }<br> --%>
${header["user-agent"] }<br>
</body>
</html>