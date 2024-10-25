<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#gugudan {
	text-align: center;
	
	width: 1100px;
	margin: 0 auto;
	border: 1px solid #ccc;
}

#gugudan .result {
	display: inline-block;
	
	width: 90px;
	border: 1px solid #ccc;
	
	margin: 5px;
	padding: 5px;
}

#gugudan .row:hover {
	background: #ccf;
}

#gugudan .row:hover .result {
	border: 1px solid #9cc;
}

#gugudan .row .result:hover {
	background: #cff;

	cursor: pointer;
	
	box-shadow: 0 0 5px 2px #bee;
/* 	이러한 효과는 인터넷에서 굉장히 많이 쓰인다 */
/*  기본적인 CSS를 안해놓으면 아무리 백엔드 개발을 잘해도 개발을 안한걸로 간주하니
   CSS를 어느정도 연습해 두자! */
}

th, td {
	text-align: center;
	
	border: 1px solid #ccc;
}
</style>

</head>
<body>

<h1>c:forEach</h1>
<hr>

<%-- 변수 i가 1부터 10까지 1씩 증가하면서 반복 --%>
<c:forEach var="i" begin="1" end="10">
	<h3>${i }</h3>
</c:forEach>

<hr>

<%-- 변수 i가 2부터 3씩 증가하면서 20까지 반복한다 --%>
<%-- ** step 속성의 기본값은 1이다 --%>
<c:forEach var="i" begin="2" end="20" step="3">
	<h3>${i }</h3>
</c:forEach>

<hr>

<%-- 1~50까지의 합 구하기 --%>

<%-- JSTL(c:forEach) 사용 --%>
<%-- JAVA 영역과 콘텍스트 영역이 분리되어 있다는 것에 유의! --%>
<%-- 총합 변수 sum 선언 --%>
<c:set var="sum" value="0"/>

<%-- i변수 1~50 반복 --%>
<c:forEach var="i" begin="1" end="50">
	<%-- i변수가 콘텍스트 안에 들어있기 때문에 자바 코드 못쓰고 대신 EL을 써야 한다 --%>
	<%-- c:set과 el을 어떻게 써야 하는지가 문제 --%>
	<%-- sum이라는 변수에다가 출력하자! 라는 개념 --%>
	<c:set var="sum" value="${sum + i }"/>
	<%-- 자바코드 -> 굉장히 복잡하다 --%>
<%-- 	<c:set var="sum" value="<%=(int)pageContext.getAttribute("sum") +  %>"/> --%>	
</c:forEach>
	<h3>합계 : ${sum }</h3>
	
<hr>

<%-- c:forEach 태그를 활용하여 2~9단 구구단 출력 --%>
<div id="gugudan">
<c:forEach var="dan" begin="2" end="9">
	
	<%-- 반복되는 div문에 id를 줄수 없다 --%>
	<%-- 반복되는 div문에는 대신 class를 줘야만 한다 --%>
	<div class="row">
	<c:forEach var="i" begin="1" end="9">
		
		<div class="result" id="result${dan }${i }">
		${dan } X ${i } = ${dan * i }
		</div>
	<%-- EL부분은 변수로 쓰일수 있으며, 최종 출력 결과는 HTML코드로 변환 --%>
	</c:forEach>
	</div>
</c:forEach>
</div>

<%-- <div>태그의 수 = 81개 --%>

<hr>

<h3>Iterator로 forEach 태그 이용하기</h3>

<%	//List 객체 
	List<String> list = new ArrayList<>();

	list.add("Alice");
	list.add("Bob");
	list.add("Clare");
	list.add("Dave");
	list.add("Edward");

	//컨텍스트 정보로 등록하기
	pageContext.setAttribute("list", list);
	
%>

<p>리스트의 길이 : ${list.size() }</p>
<%-- EL문법이 자바문법과 약간 다르기는 하지만, 일부 자바문법을 사용할 수 있다 --%>
<%-- EL내부에서의 자바문법 사용가능 여부는 일단 사용해보고 확인하자! --%>

<%-- ** 인덱스를 가진 List 객체를 이용할 때 begin, end, step 속성을
     이용하면 반복되는 인덱스를 조절할 수 있다 --%>
<%-- ** varStatus 속성을 이용하여 반복되는 상태 정보를 저장한
     변수를 선언할 수 있다 --%>
     
<c:forEach var="data" items="${list }" 
begin="1" end="3" step="2"
varStatus="stat">
	<%-- list에 담겨있던 데이터가 하나씩 data변수에 들어간다 --%>
	<%-- 이때 begin과 end에는 index값이 들어간다 --%>
	<%-- 여기서는 0~4 사이의 숫자가 가능 --%>
	<p>${data }</p>
	
	<p>인덱스: ${stat.index }</p>
	<p>실행 횟수: ${stat.count }</p>
	
	<p>first: ${stat.first }</p>
	<p>last: ${stat.last }</p>
	
	<hr>
</c:forEach>

<hr>

<h3>Iterator로 forEach 태그 이용하기</h3>

<%	//자바 Map객체 생성
	Map<Integer, String> map = new HashMap<>();
	
	map.put(1, "Apple");
	map.put(2, "Banana");
	map.put(3, "Cherry");
	map.put(4, "Durian");
%>

<%-- 자바 map객체를 context로 등록 --%>
<%-- 자바 영역 객채 map 변수를 컨텍스트 정보 m으로 등록 --%>
<c:set var="m" value="<%=map %>"/>

<%-- 컨텍스트 정보 m 변수를 이용한 반복 --%>
<c:forEach var="iter" items="${m }"></c:forEach>

<%-- 위의 두줄 코드를 한줄로 축약한 것이 아래의 코드이다 --%>

<%-- 자바 영역 객체 map 변수를 이용한 반복 --%>
<c:forEach var="iter" items="<%=map %>">
	${iter.key }=${iter.value }<br>
</c:forEach>
<%-- Map에는 Entry가 담겨있고 다시 Entry에서 key value쌍(key = value)이 담겨있다 --%>

<hr>

<%-- <table border="1"> 하면 기본 테두리 씌워준다 --%>
<%-- 테스트에 유용하지만, 지금은 Deprecated된 상태이므로 되도록 사용하지 말자 --%>
<table>
<thead>
<tr>
	<th>키, key</th>
	<th>값, value</th>
</tr>
</thead>
<c:forEach var="iter" items="<%=map %>">
<tr>
	<td>${iter.key }</td>
	<td>${iter.value}</td>
</tr>
</c:forEach>
<tbody>


</tbody>	

</table>

</body>
</html>