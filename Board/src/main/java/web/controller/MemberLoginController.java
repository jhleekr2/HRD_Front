package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import web.dto.Member;
import web.service.face.MemberService;
import web.service.impl.MemberServiceImpl;

@WebServlet("/member/login")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//컨트롤러에 서비스를 연결
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/login [GET]");

		//세션 객체 생성
		HttpSession httpSession = req.getSession();
		
		//로그인 정보가 이미 존재하는지 확인
		System.out.println("로그인 정보 : " + httpSession.getAttribute("login"));
		//나의 머릿속으로는 로그인 정보가 있으면 true, 없으면 false 뜰것으로 예상
		//실제 출력 결과 -> 정보가 있으면 true 뜨는 것은 같지만 없으면 null이 뜨며
		//타입은 예상된 boolean이 아닌 String 타입인 것으로 생각된다
		
		//접속할 때 로그인 정보가 이미 존재하는지 확인
//		if ( httpSession.getAttribute("login") == "true" ) {
		if ( httpSession.getAttribute("login") != null ) {

			//로그인 정보가 이미 있으면 메인 페이지로 강제 포워딩
			req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);	
		}
		else {
			//로그인 정보가 없으면 View로 포워딩(로그인 페이지로 포워딩)
			req.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(req, resp);
		}
		
		// 로그인 정보가 이미 존재하는지 체크해서 이미 존재하면 메인으로 리다이렉트되고
		//그렇지 않을 때 로그인 화면 View로 포워딩 하는 코드를 추가로 개발할 생각!
    
		//강사의 피드백 - 기본 구조를 빨리 완성한 후에 추가기능을 개발하는 것을 더 권장!
		//-> 로그인 정보 이미 존재하는지를 확인하는 부분의 개발은 나중으로 미룬다!
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/login [POST]");
		
		//로그인 전달 파라미터 추출하여 DTO로 변환
		Member param = memberService.getLoginParam( req );
		
		System.out.println(param);
		//id/pw가 일치하는 행의 COUNT로 처리 -> DAO -> DB -> DAO -> 컨트롤러
//		boolean login(Member);
		boolean loginResult = memberService.login( param );
		//강사는 isLogin으로 명명했다(더 바람직한 방법)
		
		//여기서 로그인이 true가 되어버림
		System.out.println( "loginResult : " + loginResult );
		
		//id를 이용해 유저 정보 가져오기 -> DAO -> DB -> DAO -> 컨트롤러
		param = memberService.info( param );
		//여기서 로그인된 유저 정보가 들어감
		
		//로그인된 회원정보 객체
		System.out.println( "로그인된 회원정보 객체 = " + param);
		
		//이후로 세션 객체를 생성하고 로그인 성공/실패 판가름
		//세션 객체를 로그인 화면 접속할 때로 넘기는게 더 좋겠다......
		//-> 테스트 결과 이미 로그인 됐는지를 체크하는 doGet 코드와
		//로그인 성공 후 세션객체를 생성하는 doPost 코드 양쪽에서
		//세션 객체 생성이 필요한 것으로 확인!
		
		//세션 객체 생성
		HttpSession httpSession = req.getSession();
		
		//이때 loginResult = true -> 로그인 성공 -> main으로 세션객체 넘김
		//     false -> 로그인 실패 -> 로그인 정보 삭제
		
//		if(param != null) {
		if( loginResult == true ) {
			//로그인 성공 시 세션 객체에 로그인 정보 등록
//			httpSession.setAttribute("login", true);
			httpSession.setAttribute("login", loginResult );
			httpSession.setAttribute("userid", param.getUserid());
			httpSession.setAttribute("usernick", param.getUsernick());
		}
		else {
			//로그인 실패 시 로그인 정보 삭제 
			httpSession.invalidate();
		}
		
		//메인으로 리다이렉트
//		req.getRequestDispatcher("/WEB-INF/views/main.jsp").forward(req, resp);
		resp.sendRedirect("/main");
	}
}
