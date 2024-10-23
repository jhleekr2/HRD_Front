package page;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/page/redirect")
public class RedirectTestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		//클라이언트에게 redirect 응답 보내기
		// -> 클라이언트는 지정된 URL로 다시 요청한다
//		resp.sendRedirect("URL");
		//-----------------------------------------------------------------------
		
		//redirect 응답하기
//		resp.sendRedirect("/actionTag/action_02.jsp");
		
		//-----------------------------------------------------------------------
	
		resp.sendRedirect("/actionTag/forwarding.jsp?data=Cherry");
	}
}
