<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* 
div.parent {
	width: 300px;

div {
<%--	width: <fmt:formatNumber value="${항목 수 / 전체 행 수}" type="percent"/> --%>
	
	background: red;
}
*/
</style>
</head>
<body>

<h1>fmt:formatNumber</h1>
<h3>숫자 데이터의 서식 지정하기</h3>
<hr>

<fmt:formatNumber value="1234567890"/>
<hr>

<fmt:formatNumber value="1234567890" type="number"/><br> <%-- 숫자 --%>
<fmt:formatNumber value="1234567890" type="currency"/><br><%-- 통화 --%>
<%-- 국가에 따라 다른 통화기호 사용될 것이다 --%>
<fmt:formatNumber value="1234567890" type="percent"/><br><%-- 퍼센트 --%>
<%-- 숫자에 100을 곱한 다음 뒤에 %가 붙는다 --%>
<%-- 퍼센트는 value값에 DB조회결과가 쓰이는 경우가 많이 있다 --%>
<%-- <fmt:formatNumber value="${항목 수 / 전체 행 수}" type="percent"/><br> --%>
<%-- 특히 이런 퍼센트 코드를 CSS코드에 써서 막대그래프를 구현하는 경우도 많다 --%>
<%-- 하지만, 해당 막대그래프 라이브러리로 Chart.js가 이미 만들어져 있어서 --%>
<%-- Chart.js를 가져와서 구현하는 것이 더 낫다 --%>

<hr>

<fmt:formatNumber value="1234567890" type="number"
 groupingUsed="false"/>
 
<hr>
 
<fmt:formatNumber value="12345.67890" type="number"
/>
<%-- 기본 모양이 소숫점 셋째자리까지 표시한다 --%>

<hr>

<fmt:formatNumber value="12345.67890" type="number"
minIntegerDigits="7"/>

<hr>

<fmt:formatNumber value="12345.67890" type="number"
maxFractionDigits="7"/>

<hr>

<fmt:formatNumber value="12345.67890" type="number"
maxFractionDigits="7"/>

<hr>
<%-- ** 정수 부분(Integer), 소수 부분(Fraction)의 최소, 최대 자리수를
조절할 수 있다 --%>
<fmt:formatNumber value="12345.67890" type="number"
maxIntegerDigits="7" minFractionDigits="7"
/>

<hr>

<fmt:formatNumber value="1234567890" type="currency"/><br>

<%-- ** currencySymbol: 통화 기호 설정 (기본값: ₩, OS설정에 따른다) --%>
<fmt:formatNumber value="1234567890" type="currency"
 currencySymbol="$"/><br>
 
<fmt:formatNumber value="1234567890" type="currency"
 currencySymbol="x"/><br>
 
<hr>

<%-- ** currencyCode: 통화 코드 설정 --%>
<%-- ISO 4217 Currency Code 통화 표준을 따른다 --%>
<fmt:formatNumber value="1234567890" type="currency"
 currencyCode="KRW"/><br>

<fmt:formatNumber value="1234567890" type="currency"
 currencyCode="USD"/><br>
 
<fmt:formatNumber value="1234567890" type="currency"
 currencyCode="JPY"/><br>
 
<%-- 유명하거나 설정된 국가에서 많이 쓰이는 통화들만 통화기호가 표시될 것이다 --%>
 <fmt:formatNumber value="1234567890" type="currency"
 currencyCode="SGD"/><br>
 
<hr>
<fmt:formatNumber value="12345.67890" pattern="######.##" /><br>
<fmt:formatNumber value="12345.67890" pattern="##.######" /><br>
<%-- # - 0이 없으면 자리를 채우지 않는다. 정수부분을 잘라내지 않고, 소수부분은 반올림해준다 --%>
<%-- 0 - 0이 없으면 0으로 자리를 채운다. 정수부분을 잘라내지 않고, 소수부분은 반올림해준다 --%>
<%-- type옵션과 pattern옵션은 서로 혼용하지 않는다 --%>
<fmt:formatNumber value="12345.67890" pattern="0000000000.0000000000" /><br>
<fmt:formatNumber value="12345.67890" pattern="00.00" /><br>

<fmt:formatNumber value="12345.67890" pattern="#.##" /><br>
<fmt:formatNumber value="12345.67890" pattern="#.##%" /><br>
<fmt:formatNumber value="12345.67890" pattern="#,###.##%" /><br>

<fmt:formatNumber value="12345.67890" pattern="$#,###.##" /><br>
<fmt:formatNumber value="12345.67890" pattern="KRW #,###" /><br>

<%-- 형식지정하는데 JSTL안쓰면 Java의 NumberFormat 쓰거나, String.format(format, args)
같은 함수 써야하는데 자바에서 형식지정이 꽤 까다롭다는 애로사항이 있다 --%>

</body>
</html>