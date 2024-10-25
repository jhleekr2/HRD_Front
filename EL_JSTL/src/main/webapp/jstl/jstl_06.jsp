<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- tablib 지시자는 불러오는 쪽에 없고 사용하는 쪽에 있다. --%>

<c:import url= "./header.jsp"/>

<h1>c:import</h1>
<hr>

<%-- ** 절대경로를 이용한 페이지 추가는 적합하지 않다 --%>
<%-- -> 추가는 되지만 자원 경로가 안 맞아 페이지가 제대로 보이지 않음 --%>
<%-- <c:import url="https://www.naver.com"/> --%>
<c:import url="https://www.google.co.kr"/>

<%-- <c:import> == <jsp:include> --%>
<%-- 동적 자원 불러오기 --%>
<%-- <%@ include %> --%>
<%-- 정적 자원 불러오기 --%>

<p>개발한 페이지</p>
<p>보여질 내용</p>

<a href="./jstl_06_another.jsp">
<button>다른 페이지</button>
</a>

<c:import url= "./footer.jsp"/>