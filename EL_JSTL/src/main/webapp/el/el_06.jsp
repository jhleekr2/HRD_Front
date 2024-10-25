<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>EL 전달 파라미터 테스트</h1>
<hr>

<form action="./el_param.jsp" method="post">

test : <input type="text" name="test"><br>
data : <input type="text" name="data">
<%-- 위의 것은 ${param.으로 처리 --%>
<hr>

드라마 <input type="checkbox" name="genre" value="drama"><br>
코믹 <input type="checkbox" name="genre" value="comic"><br>
호러 <input type="checkbox" name="genre" value="horror">
<%-- 아래 것은 ${paramValues.으로 처리 --%>
<hr>

<button>전송</button>

</form>
</body>
</html>