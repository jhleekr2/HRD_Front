<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>EL의 자료형</h1>
<hr>


정수형 : ${12345}<br>
실수형 : ${123.456}<br>

문자 : ${'A'}<br>
문자 : ${"BBB" }<br>

논리형 : ${true }<br>
논리형 : ${false }<br>

null : ${null }<br>

empty null : ${empty null }<br><br>

<small>** null 키워드를 EL로 출력하면 아무런 값도 출력되지 않는다</small>
<!-- EL의 자료형은 Java보다는 Javascript에 가깝다 -->
</body>
</html>