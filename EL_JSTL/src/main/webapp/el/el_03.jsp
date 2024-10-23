<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>EL 연산자</h1>
<hr>

${15 / 6 }<br>

<%-- ${15 div 6 }<br> --%>
<%-- div 6쪽에 에러 표시가 나지만 에러는 아니다 --%>
<%-- 이클립스가 EL 문법을 제대로 해석하지 못하는 버그 --%>
<%-- -> 이클립스로 개발할 때는 다른 연산자는 다 괜찮지만 div 만은 되도록 쓰지 말자 --%>
<hr>

${10 == 20 }<br>
${10 eq 20 }<br>

<hr>

<%-- ** EL에서 식별자(이름)를 사용하면 컨텍스트 정보에서 
	해당 이름의 Java Bean객체(컨텍스트에 등록되어 있는 자바 객체)를 찾는다 --%>

<%-- ** EL변수에 해당하는 컨텍스트 정보가 없으면 null 값을 반환한다 --%>
<%-- 그런데, EL은 null을 출력하지 않으므로 비어 있을 것이다. --%>
obj객체 : ${obj }<br>

empty obj : ${empty obj }

<hr>

${obj = "Apple" }
<%-- EL이 알아서 객체 생성, 대입까지 해주지만, 대입 후 EL이 출력을 무조건 --%>
<%-- 해버리는 문제점이 있어서 EL에서 대입 연산자는 되도록 쓰면 안된다. --%>

obj : ${obj }<br>

<hr>

<%-- ** 자바 변수는 자바 객체 영역(스코프)에 생성된다 --%>
<%-- ** JSP 컨텍스트 영역에 등록되지 않는다 --%>

<%-- ** EL은 변수를 컨텍스트 정보에서 찾는다 --%>

<%	Integer num = 66666; %>

num : ${num }<br>
empty num : ${empty num }<br>
<%-- 자바 변수가 만들어지기는 했지만 컨텍스트 영역에 등록되지 않아서 EL이 못찾음 --%>

</body>
</html>