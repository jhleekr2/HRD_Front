package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Board;
import web.dto.BoardFile;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;

@WebServlet("/board/view")
public class BoardViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//컨트롤러에 서비스를 연결
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/view [GET]");

		//전달 파라미터 얻기 - boardService 객체 이용
		Board param = boardService.getBoardno( req );
		
		System.out.println("BoardViewController doGet() - board : " + param);
	
		//상세보기 조회 - boardService 객체 이용
//		Board result = boardService.view( param );
		param = boardService.view( param );
		
//		System.out.println(result);
		System.out.println(param);
		
		//작성자의 닉네임 모델값 전달
		req.setAttribute("writernick", boardService.getWriterNick( param ));
		
		//첨부파일 정보 조회하기
		BoardFile boardFile = boardService.viewFile(param);
		
		//모델값 전달 - req 이용
//		req.setAttribute("detail", result);
		req.setAttribute("detail", param);
		req.setAttribute("boardFile", boardFile);
		//View로 포워딩
		req.getRequestDispatcher("/WEB-INF/views/board/view.jsp").forward(req, resp);
		
	}
}
