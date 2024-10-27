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
	$("#btnAction").click(function() {
		console.log("#btnAction click");
		
		//AJAX 요청 처리
		$.ajax({
			type: "post",
			url: "/ajax/test",
			data: {},
			dataType: "json", //text는 빈문자열도 성공, json은 빈문자열은 실패
			//여기서 응답 데이터 parsing까지 처리가 된다 만일, dataType이 text이면 parsing해야한다
			success: function( res ) {
				console.log("AJAX 성공");
				
				//응답 데이터 출력
				console.log( res ); //Object
// 				console.log( JSON.parse(res) );
				console.log( res.list ); //응답 데이터의 배열 -> 반복문 사용 가능
				console.log( res.list[0] );
				console.log( res.list[0].no );
				console.log( res.list[1] );				
				console.log( res.list[2] );
				console.log( "----------------" );
				console.log( "이후 자바스크립트 HTML을 써야 하는데, JS DOM이 어려워서 jQuery 사용한다");
				console.log( "조회 결과를 반복문 써서 HTML로 보여주기 어렵다는 것은 AJAX의 치명적인 단점!")
				
				$("<table>")
				.append($("<tr>")
						.append($("<td>").html( res.list[0].no))
						.append($("<td>").html( res.list[0].name))
						.append($("<td>").html( res.list[0].addr))
				)
				.appendTo( $("div#result") )					
				
				var $tbl = 
					$("<table>")
					.css("margin", "0 auto")
					.attr("border", "1");
				
				for(var i=0; i<res.list.length; i++) {
					$tbl.append(
						$("<tr>")	
						.append($("<td>").html( res.list[i].no ) )
						.append($("<td>").html( res.list[i].name ) )
						.append($("<td>").html( res.list[i].addr ) ) );
					}
					
				$tbl.appendTo( $("#result") );
					
				
				
			},
			error: function() {
				console.log("AJAX 실패");
			}
		
		})
		
	})
})
</script>
</head>
<body>

<h1>AJAX 테스트</h1>
<hr>

<button id="btnAction">AJAX 요청</button>

<!-- 응답 데이터 반영 div -->
<div id="result"></div>

</body>
</html>