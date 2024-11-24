package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Member;
import service.face.MemberService;
import service.impl.MemberServiceImpl;

@WebServlet("/member/join")
public class MemberJoinController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/join [GET]");
		
		req.getRequestDispatcher("/WEB-INF/views/member/join.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/join [POST]");
		
		// 회원가입 전달 파라미터 추출하기
		Member param = memberService.getMemberParam( req );
		
		//전달 파라미터 확인 - 실제 개발할때는 Sysout 특유의 느린 퍼포먼스 때문에 주석 처리한다
		System.out.println(param);
		
		//그냥 전달파라미터 넘겼을때는 한글이 깨지는 문제가 발생한다
		//-> 서블릿 필터/웹 필터 사용하여 한글 처리
		// 회원가입 처리
		memberService.join( param );
		
		// 조인 폼으로 리다이렉트
		resp.sendRedirect("./join");
	}
}
