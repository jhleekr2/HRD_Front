package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(value = { "/board/insert" } )에서 배열이 1개여서 배열생략, 값이 value값밖에 없어서 value = 생략
// 추가 내용이 있으면, value = {} 생략 불가
//@WebServlet("/board/insert")

//@WebServlet(value = { "/board/insert" }, name="servlet name")
//servlet name(서블릿네임) - Mapping할때 사용
//class name이 BoardController이면 servlet name = boardController

@WebServlet(value = { "/board/insert" }, name="boardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//		System.out.println("/board/insert [GET]");
		
//		System.out.println( req.getRequestURI() );
//		System.out.println( req.getMethod() );
		
//		System.out.println( req.getRequestURI() + " [" + req.getMethod() + "]");
		
		req.getRequestDispatcher("/WEB-INF/views/board/insert.jsp").forward( req , resp );
		//getRequestDispatcher 뒤에 .forward( req, resp ) 빼먹으면 빈 페이지만 뜨는 오류가 발생한다
		//특히 이러한 경우에는, 아파치 톰캣 배포 과정에서 web.xml을 비롯한 프론트엔드 파일 전체가
		//날아가서 자료의 손실이 발생할 수도 있다
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		System.out.println("/board/insert [POST]");
		
//		System.out.println( req.getRequestURI() );
//		System.out.println( req.getMethod() );
		
//		System.out.println( req.getRequestURI() + " [" + req.getMethod() + "]");
		
		//-----------------------------------------------------------------------
		
		//한글 인코딩 설정
		// -> 한글 파라미터를 처리할 때마다 작성해야한다
		// -> 서블릿 필터로 적용하도록 변경한다
//		req.setCharacterEncoding("UTF-8");
		
		System.out.println("title: " + req.getParameter("title"));
		System.out.println("content: " + req.getParameter("content"));
		
		//게시글 작성 페이지 다시 나오도록 호출
		req.getRequestDispatcher("/WEB-INF/views/board/insert.jsp").forward( req , resp );

	}
}
