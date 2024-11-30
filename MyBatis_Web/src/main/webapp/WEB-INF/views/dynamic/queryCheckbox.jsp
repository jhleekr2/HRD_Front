<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script type="text/javascript">
$(function() {
	$("#all").click(function() {
		$("input[name='deptno']".prop("checked", $(this).prop("checked"))
	})
})
</script>
</head>
<body>

<h1>동적쿼리 테스트</h1>
<h3>checkbox</h3>
<hr>

<label>전체 <input type="checkbox" id="all"></label>

<form action="./query3" method="post">

<label>10부서 <input type="checkbox" name="deptno" value="10"></label><br>
<label>20부서 <input type="checkbox" name="deptno" value="20"></label><br>
<label>30부서 <input type="checkbox" name="deptno" value="30"></label><br><br>

<button>전송</button>

</form>

</body>
</html>