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

<h1>파라미터 수정 페이지</h1>
<hr>

<a href="../file/list"><button>파일 목록</button></a><br>

<form action="./edit" method="post">
<label>데이터 번호</label><input type="text" name="datano"  value="${info.datano }" readonly><br>
<label>제목</label><input type="text" name="title"  value="${info.title }"><br>
<label>데이터1</label><input type="text" name="data1"  value="${info.data1 }"><br>
<label>데이터2</label><input type="text" name="data2"  value="${info.data2 }"><br>
<button>수정</button><!-- form action에서 ./edit를 함으로써 post기능과 연결해서 데이터 수정을 구현 -->
</form>

</body>
</html>