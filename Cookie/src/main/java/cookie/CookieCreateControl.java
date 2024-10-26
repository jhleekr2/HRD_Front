package cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie/create")
public class CookieCreateControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/cookie/create [GET]");
		//----------------------------------------------------------------
		
		//쿠키를 관리할때는 쿠키 객체를 만들도록 되어 있다
		//쿠키 객체 생성
		Cookie c1 = new Cookie("ID", "ALI"); // ID=ALI
		//이때 Cookie는 java.servlet.Cookie 사용
		
		//쿠키 정보를 클라이언트에게 응답하도록 추가
		resp.addCookie(c1); //Set-Cookie: ID=ALI (응답: 저장)
		
		// ** resp.addCookie(쿠키객체)
		// 응답 메시지에 "Set-Cookie: name=value" 항목을 추가한다
		// 응답 헤더를 확인한 브라우저는 쿠키 정보를 저장한다

		resp.addCookie(new Cookie("PASS", "1234"));
		resp.addCookie(new Cookie("NAME", "Alice"));

		// ----------------------------------------------------------------

		req.getRequestDispatcher("/WEB-INF/views/cookie/create.jsp").forward(req, resp);
	}
}
