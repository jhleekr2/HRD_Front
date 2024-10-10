package hello;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿 클래스
//@WebServlet("/hello") -> web.xml에서 이미 /hello에 매핑하고 있기 때문에 두 서블릿이 같은 경로에 매핑되어
//에러가 발생한다
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/hello [GET]");
		//서블릿부터는 Ctrl + F11을 이용한 실행을 하지 않는다
		//-> 메인 메소드 없이 브라우저로 실행하기 때문
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/hello [POST]");
	}

}
