package session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session/delete")
public class SessionDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/session/delete [GET]");
		//-------------------------------------------------------------------
		
		//세션 객체
		HttpSession session = req.getSession();
		
		//세션 삭제(로그아웃과 마찬가지)
		// -> 세션 컨텍스트 정보를 포함한 세션 전체가 삭제된다
		// -> 로그아웃 기능에 사용할 수 있다
		session.invalidate();
		
		//-------------------------------------------------------------------
		req.getRequestDispatcher("/WEB-INF/views/session/delete.jsp").forward(req, resp);
	
	}
}
