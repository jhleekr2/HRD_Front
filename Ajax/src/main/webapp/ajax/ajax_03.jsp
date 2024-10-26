<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/resources/js/httpRequest.js"></script>

<script type="text/javascript">
// AJAX 요청을 보내는 함수
function ajaxToServer() {
	console.log("ajaxToServer() call");
	
	//전달 파라미터
	var params = "username=" + document.f.username.value;
	console.log(params);
	
	//AJAX 요청 보내기
// 	sendRequest("POST", "./ajax_03_ok.jsp", params, callback);
	sendRequest("POST", "./ajax_03_ok.jsp", params, ajaxFromServer);
}

//AJAX 요청을 받는 콜백 함수
function ajaxFromServer() {
// 	console.log("ajaxFromServer() call");

	if( httpRequest.readyState == XMLHttpRequest.DONE ) {
		if( httpRequest.status == 200 ) {
			console.log("AJAX 정상 응답");
			
			//응답 데이터를 div#resultLayout에 반영하기
			resultLayout.innerHTML = httpRequest.responseText;
			
		} else {
			console.log("AJAX 요청/응답 에러");
		}
	}
}
</script>
</head>
<body>
<h1>AJAX 03</h1>
<hr>

<form name="f">
	<!--  ** form태그에 <input type="text">가 단독으로 있을 경우
	엔터 키 입력 시 자동으로 submit 한다 -> submit하면 onsubmit이벤트가 발생한다 -->
	<!-- submit 막기 위해 일부러 2개 이상의 input을 걸거나, onsubmit fail 설정을 건다 -->
	<!-- ** 해결 : display:none; 으로 비어있는 input을 추가한다 -->
	<input type="text" style="display: none;">
	
	<input type="text" name="username">
	
	<!-- button태그는 submit 동작으로 만들어진다 -->
	<button type="button" onclick="ajaxToServer()">입력 (AJAX 요청)</button>
</form>
<!-- 이슈 : 한글이 깨진다 -->
<div id="resultLayout">

</div>
</body>
</html>