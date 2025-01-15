<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/header.jsp" />
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>


<h1>회원가입</h1>
<hr>

<div class="col-8 mx auto">
<form action="<%=request.getContextPath() %>" method="post">

<div class="row mb-3">
	<label class="col-3 col-form-label" for="joinid">아이디</label>
	<div class="col-9">
		<input type="text" name="joinid" id="joinid" value="a"><br>
	</div>
</div>

<div class="row mb-3">
	<label class="col-3 col-form-label" for="joinpw">패스워드</label>
	<div class="col-9">
		<input type="text" name="joinpw" id="joinpw" value="b"><br>
	</div>
</div>

<div class="row mb-3">
	<label class="col-3 col-form-label" for="nickname">닉네임</label>
	<div class="col-9">
		<input type="text" name="nickname" id="nickname" value="c"><br>
	</div>
</div>
	
	<button class="btn btn-success">회원가입</button>
	
</form>
</div>

<c:import url="../layout/footer.jsp" />