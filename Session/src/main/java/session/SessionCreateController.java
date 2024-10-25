package session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session/create")
public class SessionCreateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/session/create [GET]");
		//-------------------------------------------------------------------
		//세션 객체 생성
		//세션 자체는 톰캣 서버가 자동으로 생성하고 세션 스코프도 정해줬음
		//이미 구축된(만들어진) 세션에 접근할 수 있도록 하는 객체 - 세션 객체
		HttpSession session = req.getSession(); //true 전달인자와 같음
		//이미 만들어진 것을 꺼내쓴다는 개념으로 생각!
		
//		HttpSession session = req.getSession(true); //기본값
//		HttpSession session = req.getSession(false);
		
		//** 기존의 HttpSession 객체가 존재하지 않을 경우 (첫 접속)
		// true 설정이면 새로운 세션 객체를 생성한다
		// false 설정이면 새로운 세션 객체를 생성하지 않고, null을 반환한다
		// -> false 설정은 정상적인 절차를 밟지 않은 비정상적 접속을 막을때 사용한다
		// -> 단점은 완벽한 설계가 되어 있지 않으면 부작용이 더 큼!
		
		//세션 컨텍스트 정보 저장하기
		session.setAttribute("test", "Apple"); //test=Apple

		//-------------------------------------------------------------------

		req.getRequestDispatcher("/WEB-INF/views/session/create.jsp").forward(req, resp);

	}
}
