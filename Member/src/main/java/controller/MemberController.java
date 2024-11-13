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

//서블릿 폴더지정 - 대충 만들고 WebServlet 안의 경로 나중에 고쳐도 된다
@WebServlet("/member/join")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//컨트롤러에 서비스를 연결
	private MemberService memberService = new MemberServiceImpl();
	
	//URL 직접 타자 -> GetMethod가 처리
	//이때 클라이언트에서 웹 서블릿 /member/join, get 옵션으로 서블릿이 요청 받았음
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/join [GET]");
		
		//JSP 파일을 응답용 View로 설정하고 forward 한다
		// -> ** forward : 응답 화면 바꾸기
//		req.getRequestDispatcher("응답용 VIEW(JSP)의 URL경로").forward(req, resp);

		req.getRequestDispatcher("/WEB-INF/views/member/joinForm.jsp").forward(req, resp);
		//이클립스 상의 webapp 폴더가 / 폴더이다

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//HttpServletRequest req안에 URL, Method, Parameter가 전부 들어 있다
		System.out.println("/member/join [POST]");
		
//		req.getRequestURI(); //요청 URL
//		req.getMethod(); //요청 메소드
//		req.getParameter(); //요청 파라미터
		
		//서블릿이 요청파라미터를 꺼내서 MODEL객체에 저장
//		String userid = req.getParameter("userid");
//		String nick = req.getParameter("nick");
//		String email = req.getParameter("email");
		
		//실제 맞는지 확인
//		System.out.println(userid);
//		System.out.println(nick);
//		System.out.println(email);
		
		//MODEL객체(DTO)에 저장
		//setter 호출대신에 생성자 매개변수를 이용했다
		//setter 세번하는 대신 생성자 매개변수 한번 이용하는 것이 코드를 좀더 짧게 할 수 있다
//		Member param = new Member(0, userid, nick, email);
//		System.out.println("MemberController param - " + param);
		//위의 두 코드를 합쳐서 다음과 같이 할 수도 있다
//		Member param = new Member(0
//				, req.getParameter("userid")
//				, req.getParameter("nick")
//				, req.getParameter("email"));
		
		//setter 호출 이용방법도 가능
		
		//위의 파라미터 꺼내기(추출, Parse)기능 구현 코드를 서비스로 옮겨보자.
		//컨트롤러 코드가 너무 길기에 컨트롤러에서 서비스로 옮기자
		//뭐든지 구현하고 싶은 서비스가 있다면 인터페이스에 먼저 적고, Impl에 오버라이딩 한다.
		//서비스에 넘기려면 매개변수로 req를 넘겨줘야 한다
		//그리고 최종 결과 Member타입 변수 param은 서비스로부터 return 받아야 한다
		
		//---------------------------------------------------------------------
		
		//전달파라미터를 DTO객체로 얻어오기
		Member param = memberService.getParam(req);
		
		//DTO객체를 Service에 join메소드로 전송
		//회원 가입 기능
		Member result = memberService.join(param); 
		//호출(call) - 호출(call)하는 코드에는 둥근괄호 안에 전달인자(인수)를 적는다
		
		//View(JSP)에 데이터 전달하기
		req.setAttribute("result", result);
		
		//View(JSP) 포워드
		req.getRequestDispatcher("/WEB-INF/views/member/result.jsp").forward(req, resp);
		
	
	}

}
