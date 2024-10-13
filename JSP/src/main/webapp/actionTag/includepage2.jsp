<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div>
<h3>추가된 페이지 영역 2</h3>

<hr>
<h3>전달 파라미터 처리</h3>

<%	String data1 = request.getParameter("data1:"); %>
<p>전달 파라미터 data1 : <%=data1 %></p>

<%-- scriptlet으로 쓴것은 ;이 있어야 하고, 직접 쓴 것은 ;이 없어야 한다 --%>
<p>data2 : <%=request.getParameter("data2") %></p>
<p>data2 : <%=request.getParameter("data3") %></p>
</div>