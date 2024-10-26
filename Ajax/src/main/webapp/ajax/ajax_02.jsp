<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script> -->

<script type="text/javascript">

var xmlHttp = null;

function calc() {
	console.log("calc() call");
	//----------------------------------------------------------------

	//XMLHttpRequest 객체 생성 (XHR 객체)
	var xmlHttp = new XMLHttpRequest();
	console.log(xmlHttp);
	
	//-- 전달 데이터 구하기

	//jQuery 방식
// 	var n1 = $("#num1").val();
// 	var n2 = $("#num2").val();
// 	var op = $("#oper").val();

	//위의 jQuery 방식에서 jQuery 걷어내고 순수 JS로 코드 다시짜면
	var n1 = document.getElementById('num1').value;
	var n2 = document.getElementById('num2').value;
	var op = document.getElementById('oper').value;
	
	console.log(n1, n2, op);
	
	//-- AJAX 요청 전 데이터 설정
	//요청 URL
	var url = "./ajax_02_ok.jsp";
	
	//요청 Method
	var method = "POST";
	
	//요청 Parameter
	var params = "num1=" + n1 + "&num2=" + n2 + "&oper=" + op;
	console.log("params", params)
	
	//-- 응답 데이터 처리 함수 지정하기
	
	//readyState 속성값이 변경될 때마다 호출하는 콜백 함수 지정하기
	// -> 이벤트 리스너
	xmlHttp.onreadystatechange = callback;
	
	//-- AJAX 요청 준비
	
	//** XHR객체를 이용하여 HTTP요청을 수행할 서버(URL)와 연결한다
// 	XHR객체.open("요청 Method", "요청 URL");
	
	//서버에 연결(요청 전송하기 전 상태)
// 	xmlHttp.open(method, url); //POST 전송
	xmlHttp.open("GET", url + "?" + params); //GET 전송
	
	//여기까지 모양은 맞췄지만, 이대로 AJAX요청을 보내면 서버는 Query String이 아니라고 생각하게 된다
	//-> AJAX 요청 데이터의 형식을 변경해야 한다
	
	//-- AJAX 요청 데이터의 형식 지정하기
	//** 요청 헤더의 Content-Type을 설정해야 한다
	xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

	//-- AJAX 요청 보내기
// 	xmlHttp.send(params); //POST 전송
	xmlHttp.send(null); //GET 전송
	
	function callback() {
		console.log('callback() called', xmlHttp.readyState);
//	 	console.log('callback() called', xmlHttp.readyState);
		
//	 	if( xmlHttp.readyState == 4 ) { //DONE
//	 	if( xmlHttp.readyState == xmlHttp.DONE ) { //DONE
		if( xmlHttp.readyState == XMLHttpRequest.DONE ) { //DONE
			console.log( 'readyState : DONE' );
			console.log( '응답 받기 완료' );
		
			if( xmlHttp.status == 200 ) { //200 OK
				console.log('--- 응답 데이터 ---');
				console.log(xmlHttp.responseText);
			
				//응답 데이터를 div#resultLayout 에 반영하기
// 				$("div#resultLayout").html( xmlHttp.responseText );
				//위의 jQuery 방식에서 jQuery 걷어내고 순수 JS로 코드 다시짜면
				document.getElementById("resultLayout").innerHTML = xmlHttp.responseText;
				
			} else {
				console.log('error', xmlHttp.status, xmlHttp.statusText);
			}
		}
	}
}

</script>
</head>
<body>

<h1>계산기</h1>
<h3>AJAX HTTP통신 (비동기식)</h3>
<hr>

<input type="text" id="num1">
<select id="oper">
	<option value="add">더하기</option>
	<option value="sub">빼기</option>
	<option value="mul">곱하기</option>
	<option value="div">나누기</option>
</select>
<input type="text" id="num2">

<button onclick="calc()"> = </button>
<%-- button 누르면 서버로 HTTP 요청이 갈 것이고, 이후 서버로부터 응답을 받을 것이다 --%>
<hr>

<!-- AJAX 요청의 응답을 적용할 DIV -->
<div id="resultLayout">
<!-- 응답객체(JS)를 HTML태그로 받아야 한다 -> DOM객체 이용! -->

</div>

</body>
</html>