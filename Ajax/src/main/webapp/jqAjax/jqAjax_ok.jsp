<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h3>응답 데이터</h3>
<hr>
<!-- find/replace 기능으로 쉽게 바꿀 수 있다 -->
<!-- num1->n1, num2->n2, oper->op 바꿀때 사용 -->
<c:choose>
	<c:when test="${param.op eq 'add' }" >
		${param.n1 } + ${param.n2 } = ${param.n1 + param.n2 }
	</c:when>
	
	<c:when test="${param.op eq 'sub' }" >
		${param.n1 } - ${param.n2 } = ${param.n1 - param.n2 }
	</c:when>
	
	<c:when test="${param.op eq 'mul' }" >
		${param.n1 } * ${param.n2 } = ${param.n1 * param.n2 }
	</c:when>
	
	<c:when test="${param.op eq 'div' }" >
		${param.n1 } / ${param.n2 } = ${param.n1 / param.n2 }
	</c:when>
	
</c:choose>

<hr>

<a href="./jqAjax.jsp"><button>다시 입력</button></a>