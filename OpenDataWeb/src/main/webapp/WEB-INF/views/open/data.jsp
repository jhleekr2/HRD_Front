<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script type="text/javascript">
$(function() {
	
	$("#btn").click(function() {
		$.ajax({
			type: "GET", //요청 메소드
			url: "https://apis.data.go.kr/1741000/TsunamiShelter4/getTsunamiShelter4List", //요청 URL
			data: {
				//Decode 키 입력
				ServiceKey: "개인의 서비스키",
// 				pageNo: 1,
				pageNo: $("#page").val(),
				numOfRows:10,
				type: "json"
			},
			dataType: "json", //응답 데이터 형식
			success: function( result ) { // 요청/응답성공 콜백 함수
				console.log("AJAX 성공");
		
				var rows = result.TsunamiShelter[1].row;
				//응답 데이터 확인
				console.log( result );
				
				var $table = $("<table>");
				
				var thead = "";
				thead += "<thead>";
				thead += "<tr>";
				
				thead += "<th>일련번호</th>";
				thead += "<th>시도명</th>";
				thead += "<th>시군구명</th>";
				thead += "<th>지진해일 대피지구명</th>";
				thead += "<th>지진해일 긴급대피장소명</th>";
				thead += "<th>소재지지번주소</th>";
				thead += "<th>경도</th>";
				thead += "<th>위도</th>";
				thead += "<th>최대수용인원수</th>";
				thead += "<th>해안선이격거리(M)</th>";
				thead += "<th>지진해일 긴급대피장소 구분</th>";
				thead += "<th>내진적용여부</th>";
				thead += "<th>해발높이</th>";
				thead += "<th>관리기관 전화번호</th>";
				thead += "<th>소재지 도로명 주소</th>";
				thead += "<th>관리기관명</th>";
				
				thead += "</tr>";
				thead += "</thead>";
				
				$table.html( thead );
			
				for(let i=0; i<rows.length; i++) {
					$table.append(
						$("<tr>")
						.append($("<td>").html( rows[i].id ))
						.append($("<td>").html( rows[i].sido_name ))
						.append($("<td>").html( rows[i].sido_name ))
						.append($("<td>").html( rows[i].sigungu_name ))
						.append($("<td>").html( rows[i].remarks ))
						.append($("<td>").html( rows[i].shel_nm ))
						.append($("<td>").html( rows[i].address ))
						.append($("<td>").html( rows[i].lon ))
						.append($("<td>").html( rows[i].lat ))
						.append($("<td>").html( rows[i].shel_av ))
						.append($("<td>").html( rows[i].lenth ))
						.append($("<td>").html( rows[i].shel_div_type ))
						.append($("<td>").html( rows[i].seismic ))
						.append($("<td>").html( rows[i].height ))
						.append($("<td>").html( rows[i].tel ))
						.append($("<td>").html( rows[i].new_address ))
						.append($("<td>").html( rows[i].manage_gov_nm ))
					);
				}
			
				//div#result에 응답 데이터 반영하기
				$("div#result").html( $table );
			},
		
			error: function() {
				console.log("AJAX 실패");
			}
		
		});
		
		
	});
	
	//접속하면 1Page 보여주기
	$("#btn").click();
	
	$("#page").focus();
});


</script>

</head>
<body>
<h1>공공데이터 AJAX 테스트</h1>
<h3>행정안전부_지진해일 긴급대피장소</h3>
<h3>지진해일 긴급대피 장소정보 조회 서비스</h3>
<hr>

<input type="text" id="page" value="1" size="2"> PAGE

<button id="btn">실행</button>
<hr>
<div id="result"></div>

</body>
</html>