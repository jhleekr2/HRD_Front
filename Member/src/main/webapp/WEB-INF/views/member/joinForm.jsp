<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>회원가입</h1>
<hr>

<!-- <form action="서버URL" method="get"> -->
<!-- <input type="submit"> -->
<!-- 입력한 사용자 정보를 button누르면 form 데이터를 전송 -->
<!-- post방식 -->
<!-- get방식은 URL주소창에 보이고, post방식은 URL주소창에 보이지 않는다 -->
<form action="/member/join" method="post">
	
	<label for="userid">아이디</label>
	<input type="text" id="userid" name="userid"><br>
	
	<label for="nick">닉네임</label>
	<input type="text" id="nick" name="nick"><br>
	
	<label for="email">이메일</label>
	<input type="text" id="email" name="email"><br><br>

	<button>회원가입</button>
	
</form>
</body>
</html>