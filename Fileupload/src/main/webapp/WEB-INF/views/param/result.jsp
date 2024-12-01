<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
input#datano {
	background-color: #ccc; 
}
</style>
</head>
<body>

<h1>전달 파라미터 수정</h1>
<hr>

<table border="1">
<tr>
	<th>번호</th>
	<td>${result.datano }</td>
</tr>
<tr>
	<th>제목</th>
	<td>${result.title }</td>
</tr>
<tr>
	<th>데이터1</th>
	<td>${result.data1 }</td>
</tr>
<tr>
	<th>데이터2</th>
	<td>${result.data2 }</td>
</tr>
</table>

<a href="../commons/fileupload">파일 업로드</a>


</body>
</html>