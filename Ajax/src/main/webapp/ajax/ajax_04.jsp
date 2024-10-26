<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/httpRequest.js"></script>

<script type="text/javascript">
//AJAX 요청을 보내는 함수
function ajaxToServer() {
	console.log("ajaxToServer() call");
	
	//전달 파라미터(2개 이상의 전달 파라미터 보내는 방법)
	var params = "username=" + username.value + "&content=" + content.value;
	
	console.log(params);

	//URL 구성
	var url = "./ajax_04_ok.jsp";
	
	//AJAX요청 보내기
// 	sendRequest("POST", "./ajax_04_ok.jsp", params, callback);
	sendRequest("POST", url, params, ajaxFromServer);
}

//AJAX 요청을 받는 콜백 함수 - 함수명을 callback이라고 써도 된다
// function callback() {
function ajaxFromServer() {
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
<h1>AJAX 04</h1>
<hr>

<!-- AJAX 사용시 한글 깨짐 문제, 2개 이상의 데이터를 AJAX로 보내고 받는 방법 관련 문제! -->
<div>

<label>이름 <input type="text" id="username" name="username"></label><br>
<label>내용 <input type="text" id="content" name="content"></label>

</div>

<button onclick="ajaxToServer()">전송</button>

<div id="resultLayout">

</div>
</body>
</html>