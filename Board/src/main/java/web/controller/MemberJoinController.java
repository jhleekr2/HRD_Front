package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Member;
import web.service.face.MemberService;
import web.service.impl.MemberServiceImpl;

@WebServlet("/member/join")
public class MemberJoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//컨트롤러에 서비스를 연결
	private MemberService memberService = new MemberServiceImpl();
      
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("member/join [GET]");
		
		//View로 포워딩
		req.getRequestDispatcher("/WEB-INF/views/member/join.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("member/join [POST]");

		// 여기서 회원가입 처리할 예정 -> 서비스로 넘어갔다가 다시 DAO -> DB -> DAO -> 컨트롤러로 돌아올 예정
	
		//회원가입 전달 파라미터 추출하기
		Member param = memberService.getJoinMember( req );
		
		//회원가입 처리
		memberService.join( param );
		
		//이후 메인 사이트로 리다이렉트
		resp.sendRedirect("/main");
	}

}
