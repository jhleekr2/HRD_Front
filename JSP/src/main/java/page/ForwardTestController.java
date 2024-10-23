package page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/page/forward")
public class ForwardTestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/page/forward [GET]");
		
		//포워드
//		req.getRequestDispatcher("/actionTag/action_01.jsp").forward(req, resp);
		
		//--------------------------------------------------------
		//전달 파라미터 추출하기
		String param = req.getParameter("data");
		System.out.println("요청 데이터 : " + param);
		//http://localhost:8088/page/forward?data=aaaaa 접속했을때
		//요청 데이터 : aaaaa가 뜬다.
		
		//--------------------------------------------------------
		
		//request 컨텍스트 정보 저장하기
		// -> 모델 값
		req.setAttribute("contextData", "안녕하세요");
		
		//--------------------------------------------------------
		
		req.getRequestDispatcher("/WEB-INF/views/forward/page.jsp").forward(req, resp);
		//외부 브라우저에서는 접속을 막는 경로이지만, 내부에서 포워딩을 통해 접속이 가능하다
	}

}
