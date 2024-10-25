package session;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session/view")
public class SessionViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/session/view [GET]");
		// -------------------------------------------------------------------

//		세션 객체
//		HttpSession session = req.getSession(false);
//		if( session == null ) {
//			System.out.println("SessionViewController - 세션 없음");
//			resp.sendRedirect("./create");
//			return;
//		}

		// "응답이 이미 커밋된 후에는 forward할 수 없습니다." 에러 발생한다.
		// 이때문에 return;추가하거나 else {} 구문을 추가해야 한다

		// -------------------------------------------------------------------

		HttpSession session = req.getSession();

//		session.isNew() : boolean
		// -> 세션이 처음 만들어졌는지 boolean으로 반환한다

		// ** 장시간(세션 유지시간)이 넘도록 아무런 조작(요청)이 없었을 때를
		// .isNew() 메소드를 이용하여 확인할 수 있다
		if (session.isNew()) {
			System.out.println("SessionViewController - 세션을 새로 만듦");
			resp.sendRedirect("./create");
			return;
		}

		System.out.println("test 세션 정보 : " + session.getAttribute("test"));

		// --- 세션 객체 관련 정보 ---

		// 세션 아이디
		System.out.println("Session ID : " + session.getId());

		// 세션을 생성한 시간 - 반환값이 Epoch Time(long)으로 반환
//		System.out.println("Creation Time : " + session.getCreationTime());
		System.out.println("Creation Time : " + new Date(session.getCreationTime()));

		// 최근(마지막) 접속한 시간 - 반환값이 Epoch Time(long)으로 반환
		System.out.println("Last Accessed Time : " + new Date(session.getLastAccessedTime()));

		// 세션 유지 시간 설정하기
		// 세션 유지 시간 = 로그인 유지 시간 으로 생각하자!
		session.setMaxInactiveInterval(3); // 3초 유지
		// ** 기본값은 1800초

		// -------------------------------------------------------------------
		req.getRequestDispatcher("/WEB-INF/views/session/view.jsp").forward(req, resp);
	}
}
