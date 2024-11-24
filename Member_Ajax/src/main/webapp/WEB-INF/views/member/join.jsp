<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">

var isCheck = false; //중복 체크 확인 변수
var isDuplicate = false; //중복 아이디 확인 변수

$(function() {
	$("#postcode").click(function() {
		console.log("#postcode click");
		
		//우편번호 검색창 열기
		new daum.Postcode({
			oncomplete: function(data) { //data : 선택한 주소의 정보
				
				$("#memberPostcode").val( data.zonecode ); //우편번호
				$("#memberAddr1").val( data.roadAddress ); //도로명 주소
				
				//상세 주소 포커스
				$("#memberAddr2").focus();
			}
		}).open();
	});
	
	//----------------------------------------------------------------
	$("#joinForm form").submit(function() { //여기서 입력여부 확인하고, 정규식으로 제약조건 걸수 있다
		
		if( !memberId.value ) {
			alert("아이디를 입력하세요");
			return false;
		}
		if( !memberPw.value ) {
			alert("패스워드를 입력하세요");
			return false;
		}
		if( !isCheck ) {
			alert("아이디를 중복체크하세요");
			return false;
		}
		if( isDuplicate ) {
			alert("이미 존재하는 아이디입니다");
			return false;
		}
	});
	
	//----------------------------------------------------------------
	
	$("#idCheck").click(function() {
// 		console.log("#idCheck click");
		
		if( !memberId.value ) {
			$("#idCheckMsg")
			.css("color", "red")
			.html("아이디를 입력하세요");
			
			//return false -> 아래쪽으로 더이상 진행하지 않음
			return false;
		}
		
		//AJAX 요청 처리
		$.ajax({
			type: "get",
			url: "./idcheck",
			data: {
				memberId: memberId.value
			},
			dataType: "json", //text는 빈 문자열도 성공, json은 빈 문자열은 실패
			//여기서 응답 데이터 parsing까지 처리가 된다. 만일, dataType이 text이면 parsing해야 한다.
			success: function( res ) {
				console.log("AJAX 성공");
				
				//아이디 중복체크 수행 상태 설정
				isCheck = true;
				
				//중복 확인 결과 설정
				isDuplicate = res.duplicate;
				
				console.log("--- 중복체크 응답 ---");
				console.log( res.duplicate );
				
				if( res.duplicate ) { // 중복 있음
					$("#idCheckMsg")
					.css("color", "red")
					.html("중복입니다");
				} else { // 중복 없음
					$("#idCheckMsg")
					.css("color", "blue")
					.html("중복이 아닙니다")
				}
			},
			error: function() {
				console.log("AJAX 실패");
				
				//아이디 중복체크 수행 상태 설정
				isCheck = false;
			}
			
		});
		
	});
	
	//----------------------------------------------------------------
	
	//아이디 창에 타이핑 했을 때 메시지 창을 지워줌
	//focus 조건 걸으면 사용자 입장에선 귀찮지만, 더 안전하다
	// -> 클릭만 해도 중복체크가 풀리기 때문
	$("#memberId").on("change input cut copy paste focus", function() {
		$("#idCheckMsg")
		.html("");
		
		//아이디 중복체크 수행 상태 설정
		isCheck = false;
	});
	
	//----------------------------------------------------------------

});
</script>
<!-- 백엔드 기능 개발 다 한 다음에 CSS를 개발해야 한다. 그렇지 않으면 개발중에 CSS가 깨진다 -->
<style type="text/css">
html {
	font-size: 24px;
}

#joinForm form {
	width: 600px;
	margin: 0 auto;
}

#joinForm div {
	margin-bottom: 0.3em;
}

#joinForm div label {
	display: inline-block;
	width: 20%;
	margin-right: 30px;
}

#joinForm div input {
	display: inline-block;
	width: 50%;
	
	line-height: 1.3em;
	font-size: 1em;
}

#joinForm div {
	position: relative;
}

#joinForm button {
	position: absolute;
	
	top: 0;
	right: -100px;
}

#joinForm div.idSection {
	position: relative;
}

#joinForm div.idSection span#idCheckMsg {
	position: absolute;
	
	diaplay: black;
	width: 300px;
	
	top: 0;
	left: 0;
	
	margin-left: 580px;
}

.btnBox {
	text-align: center;
}

button {
	font-size: 0.7em;
}
</style>
</head>
<body>

<h1>회원가입</h1>
<hr>

<div id="joinForm">

<form action="./join" method="post">

<!-- 개발순서 - 라이브러리 -> 서블릿 -> JSP -> DTO 순서 권장 -->
<div class="idSection">
	<label for="memberId">아이디</label>
	<input type="text" name="memberId" id="memberId" required="required">
	<button type="button" id="idCheck">중복확인</button>
	<span id="idCheckMsg"></span>
</div>

<div>
	<label for="memberPw">패스워드</label>
	<input type="text" name="memberPw" id="memberPw" required="required">
</div>

<div>
	<label for="memberNick">닉네임</label>
	<input type="text" name="memberNick" id="memberNick">
</div>

<div>
	<label for="memberPhone">전화번호</label>
	<input type="text" name="memberPhone" id="memberPhone">
</div>

<div>
	<label for="memberPostcode">주소</label>
	<input type="text" name="memberPostcode" id="memberPostcode" readonly="readonly">
	<button type="button" id="postcode">우편번호 찾기</button>
</div>

<div>
	<label></label>
	<input type="text" name="memberAddr1" id="memberAddr1" readonly="readonly">
</div>
<div>
	<label></label>
	<input type="text" name="memberAddr2" id="memberAddr2" placeholder="상세 주소">
</div>

<div class="btnBox">
	<button>회원 가입</button>
</div>
</form>

</div>

</body>
</html>