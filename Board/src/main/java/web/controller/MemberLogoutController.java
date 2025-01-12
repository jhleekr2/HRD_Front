package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member/logout")
public class MemberLogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/logout [GET]");
		
		//세션 객체
		HttpSession session = req.getSession();
		
		//세션 삭제(로그아웃)
		session.invalidate();
		
		//위의 코드를 한줄로 축약한 형태
		req.getSession().invalidate();
		
		//리다이렉트
//		resp.sendRedirect("/");
		resp.sendRedirect("/main");
	}
}
