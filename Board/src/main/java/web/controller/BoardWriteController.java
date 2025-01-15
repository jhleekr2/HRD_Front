package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Board;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;

@WebServlet("/board/write")
public class BoardWriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//컨트롤러에 서비스를 연결
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/write [GET]");

		//세션 객체 생성
		HttpSession httpSession = req.getSession();
		
		//로그인 정보가 이미 존재하는지 확인
		System.out.println("로그인 정보 : " + httpSession.getAttribute("login") );
		//나의 머릿속으로는 로그인 정보가 있으면 true, 없으면 false 뜰것으로 예상
		//실제 출력 결과 -> 정보가 있으면 true 뜨는 것은 같지만 없으면 null이 뜨며
		//타입은 예상된 boolean이 아닌 String 타입인 것으로 생각된다

		//접속할 때 로그인 정보가 이미 존재하는지 확인
//		if ( httpSession.getAttribute("login") == "true" ) {
		if ( httpSession.getAttribute("login") != null ) {
			
			//로그인 정보가 이미 있으면 게시글 쓰기 페이지로 포워딩
			req.getRequestDispatcher("/WEB-INF/views/board/write.jsp").forward(req, resp);	
		}
		else {
			// 로그인 정보가 없으면 로그인 페이지로 리다이렉트(게시글 쓰기가 동작하지 않음)
			resp.sendRedirect("/member/login");
			
			return;
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//글쓰기 된 결과물을 처리하기 위해 서비스 연결
		
		//전달 파라미터 얻기 - boardService 객체 이용
//		Board param = boardService.getBoardContent( req );

//		System.out.println("boardno" + param.getBoardno());
//		System.out.println("title" + param.getTitle());
//		System.out.println("content" + param.getContent());
//		System.out.println("hit" + param.getHit());
//		System.out.println("write_date" + param.getWriteDate());
		//위 코드는 파일 업로드 추가하면 먹통이 되어버림 - multipart/form-data 방식으로 바뀌었기 때문
		
		//세션 객체 생성
//		HttpSession httpSession = req.getSession();
		
		//게시글 작성자 아이디를 로그인 세션에서 얻어옴 - 이때 Object 타입을 String 타입으로 변환함
//		param.setUserid( String.valueOf( httpSession.getAttribute("userid") ) );
		
//		System.out.println(String.valueOf( httpSession.getAttribute("userid") ));
		//이후 서비스에서 DAO연결하고 DAO가 DB작업을 하면서 게시글을 등록할 것이다
//		boardService.write( param );
		
		//파일 첨부 추가해서 게시글 작성 처리하기
		boardService.write( req );
		
		//게시글 작성 끝난 후에는 /board/list로 리다이렉트
		resp.sendRedirect("/board/list");
	}
}
