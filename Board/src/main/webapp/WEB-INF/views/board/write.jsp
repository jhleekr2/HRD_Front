<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/header.jsp" />

<!-- summernote -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-bs5.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-bs5.min.js"></script>

<script type="text/javascript">
$(function() {
	
	//페이지 접속 시 제목 입력창으로 포커스 이동
	$("#title").focus();
	
	//작성 버튼 클릭시 submit
	$("#btnWrite").click(function() {
		
		//제목 필수 입력 설정
		if( !$("#title").val() ) {
// 			alert("제목을 입력하세요");

			//모달창 보여주기
			$("#msgModal").modal("toggle");
			
			return false;
		}
		
		$(this).parents("form").submit();
	});
	
	$("#btnCancel").click(function() {
		history.go(-1); //뒤로가기
// 		location.href("/"); //메인으로 가기
	});
	
	//summernote 웹 에디터 적용
	$("#content").summernote({
		height: 300 //반드시 px를 생략해야 한다
		, placeholder: "게시글을 작성하세요"
	});
	
})
</script>

<div class="container">

<h1>게시글 작성</h1>
<hr>

<div class="col-10 mx-auto">
<form action="./write" method="post" enctype="multipart/form-data">

<table class="table table-bordered">

<colgroup>
<!-- colgroup 태그는 동일한 td 전체에 CSS를 적용하기에 유리한 태그 -->
	<col class="col-3">
	<col class="col-9">
</colgroup>

<tr>
	<td class="table-info">아이디</td>
	<td>${usernick } (${userid })</td>
</tr>
<tr>
	<td class="table-info align-middle">제목</td>
	<!-- CSS에서 수직 정렬을 위해 vertical-align을 사용할 수 있다 -->
	<td>
		<input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세요">
	</td>
</tr>
<tr>
	<td class="table-info" colspan="2">본문</td>
</tr>
<tr>
	<td colspan="2">
		<textarea class="form-control" id="content" name="content" placeholder="내용을 입력하세요"></textarea>
	</td>
</tr>
</table>

<div class="mb-3">
첨부파일
<input type="file" name="file" class="form-control">
</div>

<!-- mb-5 - margin bottom 5px이라는 뜻 -->
<div class="text-center mb-5">
	<button type="button" class="btn btn-primary" id="btnWrite">작성</button>
	<button type="button" class="btn btn-danger" id="btnCancel">취소</button>
</div>

</form>
</div>

</div><!-- div.container -->

<c:import url="../layout/footer.jsp" />


<!-- <button type="button" class="btn btn-primary" data-bs-toggle="modal" -->
<!--  data-bs-target="#msgModal"> -->
<!--   Launch demo modal -->
<!-- </button> -->

<div class="modal" id="msgModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
<!--         <h5 class="modal-title">Modal title</h5> -->
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p class="text-center">제목을 입력하세요</p>
      </div>
      <div class="modal-footer">
<!--         <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button> -->
        <button type="button" class="btn btn-primary" data-bs-dismiss="modal">확인</button>
      </div>
    </div>
  </div>
</div>
