<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/header.jsp" />
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script type="text/javascript">
$(function() {
	//페이지 접속 시 아이디 입력창으로 포커스 이동
	$("#loginid").focus();
	
	//폼 태그가 submit될 때 처리
	$("form").submit(function() {
	
		if( $("#loginid").val() == '') {
			alert("아이디를 입력하세요");
			$("#loginid").focus();
			
			//submit 처리 중단
			return false;
		}
		
		if( $("#loginpw").val() == '') {
			alert("패스워드를 입력하세요");
			$("#loginpw").focus();
			
			//submit 처리 중단
			return false;
		}
		
	})
	
	//취소 버튼 클릭시 동작
	$("#btnCancel").click(function() {
		history.go(-1); //뒤로가기
// 		location.href("/"); //메인으로 가기
	})

})
</script>

</head>
<body>

<h1>로그인</h1>
<hr>

로그인 상태 : ${login }<br>
비로그인 상태 : ${empty login }<br>

<div class="col-8 mx auto">
<form action="<%=request.getContextPath() %>" method="post">

<div class="row mb-3">
	<label class="col-3 col-form-label" for="loginid">아이디</label>
	<div class="col-9">
		<input type="text" name="loginid" id="loginid" value="a"><br>
	</div>
</div>
	
<div class="row mb-3">
	<label class="col-3 col-form-label" for="loginpw">패스워드</label>
	<div class="col-9">
		<input type="text" name="loginpw" id="loginpw" value="b"><br><br>
	</div>
</div>
	<button class="btn btn-primary" id="btnLogin">로그인</button>
	<button type="button" class="btn btn-danger" id="btnCancel">취소</button>	
</form>
</div>

<c:import url="../layout/footer.jsp" />