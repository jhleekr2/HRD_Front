<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>c:choose</h1>
<hr>

<form action="./jstl_03_choose.jsp" method="post">

<select name="sel">
	<option value="0"> 선택안함</option>
	<option>1</option>
	<option>2</option>
	<option>3</option>

</select>
<%-- sel=3이라는 전달파라미터가 전달될 것이다 --%>
<button>전송</button>

</form>
</body>
</html>