package hello;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 서블릿의 자바 설정을 헌재는 XML과 annotation(@)을 쓰지만, 앞으로는 Javaconfig로 넘어가게 될 것 같다
// 이 수업에서는 XML과 annotation 위주로 다룬다
@WebServlet("/hi")
public class HiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//톰캣 서버는 메소드 추가된 것을 인식하지 못해서, 메소드 추가 후에는 서버 재부팅 필요하다
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/hi [GET]");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
