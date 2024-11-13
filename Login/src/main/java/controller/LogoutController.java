package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/login/logout [GET]");
	
		//세션 객체
//		HttpSession session = req.getSession();
		
		//세션 삭제(로그아웃)
//		session.invalidate();
		
		//위의 코드를 한줄로 줄이면
		req.getSession().invalidate();
		
		//리다이렉트
//		resp.sendRedirect("/login/login");
		resp.sendRedirect("/");
		System.out.println("세션 삭제 후 로그인 페이지로 리다이렉트");
	}
}
