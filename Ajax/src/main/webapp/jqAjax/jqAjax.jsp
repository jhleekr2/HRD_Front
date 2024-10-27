<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<!-- 이번에는 jQuery를 이용한 AJAX 구현해본다 -->
<script type="text/javascript">
$(function() {

	//자바스크립트에서 중괄호가 plain text 타입
	// ** $.ajax() 함수의 기본 사용 구조
// 	$.ajax({
// 		type: "", // 요청 메소드(GET|POST)
// 		url: "", // 요청 URL
// 		data: {  // 요청 파라미터 - Query String(name=value)형식으로 원래 보내짐
// 			// name:value 형식으로 적음
// 			key: value,
// 			key2: value2,
// 			key3: value3, ....
// 			} // 이런식으로 쓰면 jQuery가 알아서 Query String으로 변경해줌
// 		}, // 이 부분을 경계로 위쪽은 요청정보, 아래쪽은 응답정보로 구분됨
// 		dataType: "", // 응답 데이터의 형식(text, html, json, xml) - 잘못적으면 오류발생!
// 		success: function( result ) {},
// 		// success(요청/응답 성공) 콜백 함수
//		// ** result 매개변수는 응답 데이터를 저장한다
// 		error: function() {}
// 		// 요청/응답 실패 콜백 함수
// 	});
	
	// ** 응답 데이터의 형식
	// text -> text/plain
	// html -> text/html
	// json -> application/json
	// xml -> 최근에는 사용하지 않는다
	
	//------------------------------------------------------------------
	
	//첫 입력 위치 조정
	$("#num1").focus();
	
	$("#btnCalc").click(function() {
		$.ajax({
			type: "get", // 요청 메소드 (GET|POST)
			url: "./jqAjax_ok.jsp", // 요청 URL
			data: { // 요청 파라미터, name:value 형식으로 적는다.
				n1: $("#num1").val(),
				n2: $("#num2").val(),
				op: $("#oper").val()
			}, // 위쪽은 요청정보, 아래쪽은 응답정보
			dataType: "html", // 응답 데이터의 형식
			success: function( result ) { // 요청/응답 성공 콜백 함수
				//** result 매개변수는 응답 데이터를 저장한다
				console.log("AJAX 성공");
				
				// 응답 데이터 확인
				console.log( result );
				
				// div#result에 응답 데이터 반영하기
				$("div#result").html( result );
				
				// jQuery 사용시 숫자 오버플로우에 취약한 문제점이 확인되었다!
				
				// 입력창 초기화
				$("#num1").val("");
				$("#num2").val("");
//	 			$("#oper").val("add");
				$("#oper").val("");
				
				// 입력 위치 조정
				$("#num1").focus();
			},
			error: function() { // 요청/응답 실패 콜백 함수
				console.log("AJAX 실패");
			}
			
		});
		
	});
	
});
</script>
<body>

<h1>jQuery AJAX 계산기</h1>
<h3>$.ajax() 이용</h3>
<hr>

<input type="text" id="num1">
<select id="oper">
	<option value="add">더하기</option>
	<option value="sub">빼기</option>
	<option value="mul">곱하기</option>
	<option value="div">나누기</option>
</select>
<input type="text" id="num2">

<button id="btnCalc"> = </button>
<hr>

<div id="result"></div>

</body>
</html>