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

<h1>파일 목록</h1>
<hr>

<a href="../commons/fileupload"><button>파일 업로드</button></a>

<table border="1">
<tr>
	<th>파일 번호</th>
	<th>원본 이름</th>
	<th>저장된 이름</th>
	<th>데이터 번호</th>
</tr>

<%-- ${list }  --%>

<%-- forEach는 var(내부변수)와 items(컨텍스트) 가 핵심 --%>

<%-- 여기서 items = Controller에서 req.setAttribute("list", list); 를 통해 전달한 list --%> 
<c:forEach var="uploadfile" items="${list }">
<tr>
	<td>${uploadfile.fileno }</td>
	<td>
		<a href="../upload/${uploadfile.storedName }" download="${uploadfile.originName }">
		${uploadfile.originName }</a>
		<%-- 원래는 파일 다운로드까지 구현해야하지만 지금은 구현 X --%>
		<%-- 다운로드는 Spring 프레임워크 배운후에 업로드 다시 구현하면서 같이 배울 예정 --%>
		<%-- 위의 다운로드 구현방식은 사실 가장 간단한 구현 방식이지만 보안상 약점때문에 --%>
		<%-- 많이 쓰이진 않는다. --%>
	</td>
	<td>${uploadfile.storedName }</td><%-- 원래는 storedName을 안알려줘야 한다 --%>
	<td>
		<a href="/param/edit?datano=${uploadfile.datano }">
			${uploadfile.datano }
		</a>
	</td>
</tr>
</c:forEach>
</table>

</body>
</html>