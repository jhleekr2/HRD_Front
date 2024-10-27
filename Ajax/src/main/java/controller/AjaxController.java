package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.Person;

@WebServlet("/ajax/test")
public class AjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/ajax/test.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//응답 데이터 형식 지정하기
		//이제까지 브라우저 -> 서버 요청 데이터형식은 쿼리스트링
		//서버 -> 브라우저 응답 데이터형식은 text/html이었음
		//이번에는 서버 -> 브라우저 응답 데이터 형식을 application/json으로 바꿈
//		resp.setContentType("text/html; charset=UTF-8"); //HTML형식
		resp.setContentType("application/json; charset=UTF-8"); //JSON형식
		
		//응답 출력 스트림 객체
		// -> 서블릿에서 직접 응답 가능(그동안은 서블릿에서 응답 못하고 JSP로 포워딩했었음)
		PrintWriter out = resp.getWriter();
		//----------------------------------------------------------------
		
		//JSON Text 응답하기
//		out.append("{}");
//		out.append("{\"test\":\"응답 데이터\"}");
		
		//----------------------------------------------------------------

		//Gson 객체
		Gson gson = new Gson();
		//----------------------------------------------------------------
		
		//DTO 객체로 JSON 응답하기
		Person p = new Person( 1, "Alice", "Seoul" ); //앞으로는 DB 조회할 것이다
		
//		out.println( 응답데이터 );
//		out.println( gson.toJson( p ) );
		//----------------------------------------------------------------
		
		
		List<Person> list = new ArrayList<>();
		list.add( new Person(1, "Alice", "AAA") );
		list.add( new Person(2, "Bob", "BBB") );
		list.add( new Person(3, "Clare", "CCC") );
		
		//[ { "no":1, "name":"Alice", "addr" } ] 형태로 바뀔 것이다
		//한번 더 중괄호를 씌워줘야 AJAX 응답을 할 수 있다.
		//따라서 Map으로 한꺼풀 더 씌워줘야 한다(Map은 {}형태로 저장되므로)
		
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		
		out.append( gson.toJson(map) );
	}
}
