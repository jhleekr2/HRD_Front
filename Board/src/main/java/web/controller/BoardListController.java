package web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Board;
import web.service.face.BoardService;
import web.service.impl.BoardServiceImpl;

@WebServlet("/board/list")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//컨트롤러에 서비스를 연결
	private BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/board/list [GET]");
		
		//전달파라미터를 이용해서 현재 페이징 객체 알아내기
//		Paging paging = boardService.getPaging(req);
		
		//페이징 객체를 MODEL값으로 전달
//		req.setAttribute("paging", paging);
		
		//서비스의 getList() 함수를 호출 - 컨트롤러 -> BoardService -> BoardServiceImpl (서비스)로 전달
		//이후 서비스가 다시 DAO를 호출하고, DAO가 DB연결 함수를 호출하며
		//DAO가 호출한 DB연결 함수를 MyBatis를 통해 다루는 형태로 백단이 진행된다.
		//이때 List<> list에서 <>안에는 DTO인 Board가 들어가야 한다(이 부분에서 막혔었음!)
		
		//사용법 - List<DTO> 반환값을 저장할 변수 = 서비스.메소드(전달값);
		//게시글 전체 조회
		List<Board> list = boardService.getList();
		
		//[TEST] 조회 결과 확인
//		if( list == null )	System.out.println("list 반환값 없음");
//		else	for( Board b : list)	System.out.println(b);
		
		// 스코프에 반환값을 추가 - 여기서 "boardList"는 View에서 그대로 적어야 하는 부분!
        req.setAttribute("boardList", list);
        
        // View로 포워딩
        req.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(req, resp);
	}


}
