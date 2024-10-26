<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>응답 데이터</h3>
<hr>

<c:choose>
	<c:when test="${param.oper eq 'add' }" >
		${param.num1 } + ${param.num2 } = ${param.num1 + param.num2 }
	</c:when>
	
	<c:when test="${param.oper eq 'sub' }" >
		${param.num1 } - ${param.num2 } = ${param.num1 - param.num2 }
	</c:when>
	
	<c:when test="${param.oper eq 'mul' }" >
		${param.num1 } * ${param.num2 } = ${param.num1 * param.num2 }
	</c:when>
	
	<c:when test="${param.oper eq 'div' }" >
		${param.num1 } / ${param.num2 } = ${param.num1 / param.num2 }
	</c:when>
	
</c:choose>

<hr>

<a href="./ajax_02.jsp"><button>다시 입력</button></a>
