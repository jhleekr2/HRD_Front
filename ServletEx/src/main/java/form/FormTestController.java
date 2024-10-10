package form;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form.do")
public class FormTestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/form.do [GET]");
		// ---------------------------------------------------

		// 요청에 대한 처리를 다른 자원으로 넘겨주는 객체
		RequestDispatcher rd;

		// 요청정보객체(req)를 이용해서 RequestDispatcher 객체를 준비한다
		// -> doGet()에 전달된 요청(request) 정보를
		// 다른 자원(JSP)으로 넘겨줄 준비를 한다
		rd = req.getRequestDispatcher("/WEB-INF/views/inputForm.jsp");
		// 프로젝트의 webapp/WEB-INF/views/inputForm.jsp

		// 지정된 JSP(View)파일로 요청 처리를 넘긴다
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// HttpServletRequest req - 요청객체
		// HttpServletResponse resp - 응답객체
		System.out.println("/form.do [POST]");
		// ------------------------------------------------------------

		// 요청 데이터의 한글 인코딩 방식 지정하기 : UTF-8
		req.setCharacterEncoding("UTF-8");

		// ------------------------------------------------------------
		// 아이디: userid
		// 패스워드: userpw

//		String req.getParameter("name")
		// -> 요청 데이터의 name을 이용하여 요청 전달 값을 반환한다

		String uid = req.getParameter("userid");
		String upw = req.getParameter("userpw");

		System.out.println("uid : " + uid);
		System.out.println("upw : " + upw);

		// 클라이언트 브라우저에서 톰캣 서버에 데이터를 전달하고 서블릿에 담아주고 있다
		// 이때 한글을 전달하면 깨지는데, 그 이유는 톰캣 서버가 아스키 코드 기반 인코딩이기 때문!
		// 그래서 원본 데이터가 그대로 남아 있음에도 불구하고 서블릿이 아스키 코드처럼 한글을
		// 1바이트 단위로 처리한다 -> 한글 깨짐!
		// -> 요청 데이터의 한글 인코딩 방식 미리 지정!

		// ------------------------------------------------------------

		// ** 서블릿에서 브라우저(클라이언트)로 데이터(화면)을 응답하기
		// 위한 스트림 객체

		// 응답 문자 출력 스트림
//		resp.getWriter() : PrintStream

		// 응답 바이트 출력 스트림
//		resp.getOutputStream() : ServletOutputStream

		// ------------------------------------------------------------

		// 응답 데이터의 형식 지정하기
		// -> 텍스트 기반의 HTML 문서
		// -> 한글 인코딩 UTF-8 적용
		resp.setContentType("text/html; charset=UTF-8");

		resp.getWriter()
			.append("<h1>전달받은 데이터</h1>")
			.append("<hr>")
			.append("<h3>아이디 : " + uid + "<h3>")
			.append("<h3>패스워드 : " + upw + "<h3>");

		// resp.getWriter()(응답 출력 스트림) - System.out(콘솔 출력 스트림)
		// .append - .println(출력 메소드)

		resp.getWriter()
			.append("<hr>")
			.append("<a href='./form.do'>입력 폼으로</a>");
		// 이때 링크 클릭하는 것은 주소창에 주소 입력하는 것과 같다

		// 이제 여기서 DB를 연결하여 반응형 웹을 개발한다

		// 위에서 보듯, 자바로의 웹개발은 너무 번거롭고 어렵다
		// -> 자바의 번거롭고 어려운 웹개발 문제를 보완하기 위해 JSP가 나왔고
		// JSP는 MVC패턴에서의 뷰에 해당한다
	}
}
