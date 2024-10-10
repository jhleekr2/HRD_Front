<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%! //선언 태그, Declaration %>

<%! //멤버 필드 선언
	private int age = 11;
	
	//멤버 메소드 정의
	public void method() {
		System.out.println("Hello " + age);
	}
%>

<%---------------------------------------------------------- --%>

<%! //선언 태그에서 메소드 호출할 수 없다
//	method();

	//수행 코드 작성 불가
//	System.out.println("HHIHI");
%>

<%---------------------------------------------------------- --%>

<%-- 선언코드로 정의한 멤버를 이용할 때 스크립트릿 태그를 이용한다 --%>
<% this.method(); %>
<%-- JSP가 나온 취지는 웹개발할때 자바를 좀 덜 쓰면서 웹개발을 좀 편하게 할수 있도록 나온건데 --%>
<%-- 실제 JSP를 깊게 사용하려면 자바에 대한 깊은 이해가 필요하다 --%>
<%-- 현실적으로는 JSP를 뷰 목적으로만 사용하기 때문에 JSP의 선언 코드는 사용하지 않는다 --%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>