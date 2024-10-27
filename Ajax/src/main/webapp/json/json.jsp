<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

//자바스크립트 객체
// var jsObj = {}
var jsObj = { "a":1, "b":2, c:"Cherry" }; //Object 타입

console.log("--- JS 객체 ---");
console.log( jsObj );

//직접 JSON텍스트로 변환하기
var jsonStr = "{ \"a\":1, \"b\":2, c:\"Cherry\" }"; // "{JS Object}" -> JSON Text
var jsonStr = '{ "a":1, "b":2, c:"Cherry" }'; // '{JS Object}' -> JSON Text

//---------------------------------------------------------------------

//JS 객체 -> JSON 텍스트 변환(마샬링) - JSON API(내장객체) 사용
var jsonText = JSON.stringify( jsObj );
console.log("--- JSON 텍스트 ---");
console.log( jsonText ); // String 타입

//JSON 텍스트 -> JS 객체 변환(언마샬링) - JSON API(내장객체) 사용
var jsonObj = JSON.parse( jsonText );
console.log("--- 복원된 JS 객체 ---");
console.log( jsonObj );
</script>
</head>
<body>

</body>
</html>