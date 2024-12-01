<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>파일 업로드</h1>
<h3>Commons Fileupload</h3>
<hr>

컨텍스트 루트 패스 : <%=request.getContextPath() %>
<hr>

<%-- 컨텍스트 루트 패스가 있으면 아래의 경로에 반드시 컨텍스트 루트를 앞에 넣어줘야 한다 --%>
<!-- <form action="/commons/fileupload"> -->
<%-- 사실 모든 웹개발에서 경로는 다음과 같이 적는 것이 가장 맞다 --%>
<%-- <form action="<%=request.getContextPath() %>/commons/fileupload"></form> --%>
<%-- 그나마 다음과 같이 적는 것이 더 낫다 --%>
<!-- <form action="./fileupload"> -->
<%-- 같은 페이지 URL이면 URL을 생략할 수도 있다. 하지만 실제 개발에서는 생략하지 않는다 --%>

<form action="./fileupload" method="get">

</form>

<!-- <form action="./fileupload" method="post" enctype="application/x-www-form-urlencoded"> -->
<!-- 기본 enctype이 application/x-www-form-urlencoded인데 이것 때문에 파일 전송이 불가하다 -->
<form action="./fileupload" method="post" enctype="multipart/form-data">
	<label>제목 <input type="text" name="title"></label><br>
	<label>데이터1 <input type="text" name="data1"></label><br>
	<label>데이터2 <input type="text" name="data2"></label><br><br>
	<!-- 반드시 text든 file이든 name옵션을 지정해야 전송이 가능하다 -->
	<label>파일 <input type="file" name="upfile"></label><br><br>
	<button>전송</button>
</form>

</body>
</html>