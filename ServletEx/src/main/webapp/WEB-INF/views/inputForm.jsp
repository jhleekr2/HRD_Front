<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>JSP 화면 테스트</h1>
<hr>

<form action="./form.do" method="post">

<label for="userid">아이디</label>
<input type="text" name="userid" id="userid"><br>

<label for="userpw">패스워드</label>
<input type="text" name="userpw" id="userpw"><br>

<button>로그인</button>
</form>
<!-- 같은 페이지라도 URL 직접입력 - get -->
<!-- 새로고침했을 때는 이미 post 연결되어 있을 때에는 post, 그렇지 않을 때는 get -->
</body>
</html>