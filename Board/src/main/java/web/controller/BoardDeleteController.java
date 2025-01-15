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

@WebServlet("/board/delete")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//컨트롤러에 서비스를 연결
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/delete [GET]");
		
		//전달 파라미터 얻기 - boardService 객체 이용
		Board param = boardService.getBoardno( req );
		
		System.out.println("BoardDeleteController doGet() - board : " + param);

		//세션 객체 생성
		HttpSession httpSession = req.getSession();
		
		//접속할 때 로그인 정보가 이미 존재하는지 확인
		if ( httpSession.getAttribute("login") != null ) {

			//로그인 정보가 이미 있으면 로그인한 사용자가 게시물 작성자가 맞는지 확인하고
			//게시글을 작성한 사용자가 아니면 삭제 처리 중단하기
			if( !boardService.checkUserid( req ) ) {
				
				resp.sendRedirect("./list");
				
				//불법 접속으로 간주하고 강력한 제재조치 취함(강제 로그아웃)
//				resp.sendRedirect("../member/logout");
				return;
			}
			
		} else {
			// 로그인 정보가 없으면 로그인 페이지로 리다이렉트(게시글 수정이 동작하지 않음)
			resp.sendRedirect("/member/login");
			
			return;
		}
		
		//게시글 삭제
		boardService.delete( req );
		
		//목록으로 리다이렉트
		resp.sendRedirect("./list");
	}
}
