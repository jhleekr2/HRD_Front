package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dto.Login;
import service.face.LoginService;
import service.impl.LoginServiceImpl;

@WebServlet("/login/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LoginService loginService = new LoginServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/login/login [GET]");
		//---------------------------------------------------------------------
		
		//View로 포워딩
		req.getRequestDispatcher("/WEB-INF/views/login/loginForm.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/login/login [POST]");
		//---------------------------------------------------------------------
		
		//로그인 전달 파라미터 추출을 서비스에 맡겨버린다.
		
		//로그인 전달 파라미터 추출하기
		Login param = loginService.getLoginParam(req);
		System.out.println("LoginController doPost() - param : " + param);
		
		//로그인 인증 처리 (ID/PASS 확인)
		Login loginResult = loginService.loginProcess( param );
		//데이터가 있으면 로그인 성공, 없으면 실패
		
		//세션 객체 생성
		HttpSession httpSession = req.getSession();
		
		if( loginResult != null ) {
			//로그인 성공
			httpSession.setAttribute("login", true);
			httpSession.setAttribute("loginno", loginResult.getLoginno()); //PK
			
			req.getRequestDispatcher("/WEB-INF/views/login/loginSuccess.jsp").forward(req, resp);
		} else {
			//로그인 실패
			req.getRequestDispatcher("/WEB-INF/views/login/loginFail.jsp").forward(req, resp);
			
			//로그인 실패 시 로그인 정보 삭제하기
			httpSession.invalidate();
		}
	}
}
