package cookie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie/update")
public class CookieUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/cookie/update [GET]");
		//----------------------------------------------------------------
		
		//ID=ALI
//		쿠키 키값이 중복되면 Map처럼 value만 바꾼다
		Cookie c1 = new Cookie("ID", "Boo");
		//여기까지는 value 바뀐거는 적용 안됨
		//반드시 다음의 코드까지 써야 적용된다.
		resp.addCookie(c1); // Set-Cookie: ID=Boo
		
		// * 쿠키의 생성 방법 == 수정 방법
		
		//PASS=1234
		Cookie c2 = new Cookie("PASS", "8769"); // 이때 숫자역시 String타입으로 맞춰줘야 한다
		
		c2.setMaxAge(3); //3초 동안 쿠키 유지(기본값 = 1800초(30분) )
//		c2.setMaxAge(1 * 24 * 60 * 60); //1일 동안 쿠키 유지
//		c2.setMaxAge(30 * 24 * 60 * 60); //30일
//		c2.setMaxAge(0); //0초(사실상 쿠키 삭제 효과)
		
		resp.addCookie(c2);
		
		//쿠키의 name, value는 대소문자를 구분한다
		resp.addCookie( new Cookie("name", "bob") );
		//----------------------------------------------------------------

		req.getRequestDispatcher("/WEB-INF/views/cookie/update.jsp").forward(req, resp);
	}
}
