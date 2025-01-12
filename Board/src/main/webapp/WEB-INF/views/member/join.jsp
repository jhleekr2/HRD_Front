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

<form action="<%=request.getContextPath() %>" method="post">

	<label for="joinid">아이디 <input type="text" name="joinid" id="joinid" value="a"></label><br>
	<label for="joinpw">패스워드 <input type="text" name="joinpw" id="joinpw" value="b"></label><br>
	<label for="nickname">닉네임 <input type="text" name="nickname" id="nickname" value="c"></label><br>
	
	<button>회원가입</button>
	
</form>

</body>
</html>