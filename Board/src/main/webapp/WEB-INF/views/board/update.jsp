<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:import url="../layout/header.jsp" />

<!-- summernote -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-bs5.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.9.0/dist/summernote-bs5.min.js"></script>

<script type="text/javascript">
$(function() {
	
	//페이지 접속 시 내용 입력창으로 포커스 이동
	$("#content").focus();
	
	//수정 버튼 클릭시 submit
	$("#btnUpdate").click(function() {
		
		//제목 필수 입력 설정
		if( !$("#title").val() ) {
			
			//모달창 보여주기
			$("#msgModal").modal("toggle");
			
			return false;
		}
		
		$(this).parents("form").submit();
	});
	
	$("#btnCancel").click(function() {
		history.go(-1); //뒤로 가기
	});
	
	//summernote 웹 에디터 적용
	$("#content").summernote({
		height: 300 //반드시 px를 생략해야 한다
	});
	
	//첨부파일 처리
	
	//첨부 파일이 없을 경우
	if( ${empty boardFile } ) {
		$("#beforeFile").hide();
		$("#afterFile").show();
	}
	
	//첨부 파일이 있을 경우
	if( ${not empty boardFile } ) {
		$("#beforeFile").show();
		$("#afterFile").hide();
	}
	
	//파일 삭제 버튼(아이콘) 클릭할 때 처리
	$("#delFile").click(function() {
		
// 		$("#beforeFile").toggle();

		$("#beforeFile").toggleClass("text-decoration-line-through");
		$("#afterFile").toggle();
		
		//파일삭제 체크박스 토글
		$(this).next("input[type='checkbox']").prop("checked", function() {
			return !$(this).prop("checked");
		})
		
	})
	
	
	$("#btnDelete").click(() => location.href = "./delete?boardno=${param.boardno}");
})
</script>

<div class="container">

<h1>게시글 수정</h1>
<hr>

<div class="col-10 mx-auto">
<form action="./update" method="post" enctype="multipart/form-data">

<input type="hidden" name="boardno" value="${detail.boardno }">

<table class="table table-bordered">

<colgroup>
<!-- colgroup 태그는 동일한 td 전체에 CSS를 적용하기에 유리한 태그 -->
	<col class="col-3">
	<col class="col-9">
</colgroup>

<tr>
	<td class="table-info">아이디</td>
	<td>${writernick } (${userid })</td>
</tr>
<tr>
	<td class="table-info align-middle">제목</td>
	<!-- CSS에서 수직 정렬을 위해 vertical-align을 사용할 수 있다 -->
	<td>
		<input type="text" class="form-control" id="title" name="title" value="${detail.title }">
	</td>
</tr>
<tr>
	<td class="table-info" colspan="2">본문</td>
</tr>
<tr>
	<td colspan="2">
		<textarea class="form-control" id="content" name="content">${detail.content }</textarea>
	</td>
</tr>
</table>

<div class="mb-3">
<div id="beforeFile">
	<c:if test="${not empty boardFile }">
	기존 첨부파일
	<a href="../upload/${boardFile.storedName }" download="${boardFile.originName }">
		${boardFile.originName }
	</a>
	
	<span id="delFile">
		<i class="bi bi-trash-fill"></i>
	</span>
	<input type="checkbox" name="delCheck" value="y" class="d-none">
	
	</c:if>
</div>

<div id="afterFile">
	새 첨부파일
	<input type="file" name="file" class="form-control">
</div>

</div>
<!-- mb-5 - margin bottom 5px이라는 뜻 -->
<div class="text-center mb-5">
	<button type="button" class="btn btn-warning" id="btnUpdate">수정</button>
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
