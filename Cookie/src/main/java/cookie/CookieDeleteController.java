package cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie/delete")
public class CookieDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/cookie/delete [GET]");
		//----------------------------------------------------------------
		
		Cookie[] cookies = req.getCookies();
		
		for( Cookie c : cookies ) {
			c.setMaxAge(0); //즉시 삭제
//			c.setMaxAge(3); //3초 뒤 삭제
			
			resp.addCookie(c);
		}
		//요즘은 아파치 톰캣 서버가 만들어낸 JSESSIONID를 브라우저가 보호해서 지워지지 않는다
		//JSESSIONID는 세션 관리를 통해 삭제할 수 있다
		req.getRequestDispatcher("/WEB-INF/views/cookie/delete.jsp").forward(req, resp);
	}
}
