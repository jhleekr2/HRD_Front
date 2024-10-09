<%@page import="dto.Emp"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% //JSP 스크립트립(scriptlet) 태그 
   // -> 자바 코드를 작성할 수 있다
%>

<%	//Servlet에서 forward 해준 객체를 empList변수에 받아서 사용한다
	List<Emp> empList = (List<Emp>) request.getAttribute("eList");
	//setAttribute로 넘어온 객체의 자료형이 Object타입
	//원본 자료형에 상관없이 무조건 오른쪽은 Object타입을 반환한다
	//그런데, 그렇게 되어서 왼쪽과 오른쪽이 형이 맞지 않기 때문에
	//Object타입으로 반환된 오른쪽을 원래의 자료형으로 형변환해야만 한다.
	//그런데, API상의 문제로 인해 warning이 뜬다.(이 경고는 해결법이 없음)
%>

<% //최근에는 JSP 스크립트립 태그를 통한 자바 코드를 쓰지 않는 것을 강력히 권장! %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	border: 1px solid #ccc;
	border-collapse: collapse;
	
	width: 1100px;
	margin: 0 auto;
}

td {
	border-top: 1px solid #ccc; 
	text-align: center;
	padding: 5px 10px
}

tr:hover {
	background-color: tomato;
}

td:hover {
	background-color: blue;
}
</style>
</head>
<body>

<h1>사원 목록</h1>
<hr>

<table>
<colgroup>
	<col style="width: 5%">
	<col style="width: 20%">
	<col style="width: 15%">
	<col style="width: 10%">

	<col style="width: 15%">
	<col style="width: 15%">
	<col style="width: 15%">
	<col style="width: 5%">
<thead>
<tr>
	<th>사번</th>
	<th>사원이름</th>
	<th>직무</th>
	<th>담당자</th>
	<th>입사날짜</th>
	<th>급여</th>
	<th>상여금</th>
	<th>부서</th>
</tr>
</thead>	

<tbody>

<% for(int i=0; i<empList.size(); i++) { %>
<tr>
	<td><%=empList.get(i).getEmpno() %></td>
	<td>
		<a href="./detail?empno=<%=empList.get(i).getEmpno() %>"> 
		<!-- 현재 위치 http://localhost:8085/emp/list -->
		<!-- 링크 클릭하면 http://localhost:8085/emp/detail로 이동 -->
		<!-- 상세 정보에 대한 서블릿이 추가되어야 한다. -->
		<!-- 페이지 정보는 같지만, 사원들의 세부사항이 서로 다르게 ?를 끝에 붙인다 -->
		<%=empList.get(i).getEname() %>
		</a>	
	</td>
	<td><%=empList.get(i).getJob() %></td>
	<td><%=empList.get(i).getMgr() %></td>
	<td><%=empList.get(i).getHiredate() %></td>
	<td><%=empList.get(i).getSal() %></td>
	<td><%=empList.get(i).getComm() %></td>
	<td><%=empList.get(i).getDeptno() %></td>
</tr>
<% } %>
</tbody>

</table>

<!-- 여기서 드디어 MVC구조에 맞춰서 DB자료를 꺼내서 조회하는 첫번째 웹 개발에 성공! -->
<!-- 앞으로 취업해서 허구헌날 하게 될 일! -->

</body>
</html>