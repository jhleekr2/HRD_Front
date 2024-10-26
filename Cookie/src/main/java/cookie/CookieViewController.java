package cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie/view")
public class CookieViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/cookie/view [GET]");
		//----------------------------------------------------------------
		
		//요청정보에서 모든 쿠키 가제오기
//		req.getCookies(); //반환 타입 - 배열(여러개가 올수 있으므로)
		Cookie[] cookies = req.getCookies();
		
		for( Cookie c : cookies ) {
			System.out.println( c );
			
			System.out.println( c.getName() + "=" + c.getValue() );
			System.out.println("---------------");
		}

		req.getRequestDispatcher("/WEB-INF/views/cookie/view.jsp").forward(req, resp);
	}
}
