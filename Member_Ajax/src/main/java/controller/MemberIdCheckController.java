package controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dto.Member;
import service.face.MemberService;
import service.impl.MemberServiceImpl;

@WebServlet("/member/idcheck")
public class MemberIdCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//서비스 객체
	private MemberService memberService = new MemberServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/member/idcheck [GET]");

		//전달 파라미터 얻기 - memberService 객체 이용
		Member idCheckParam = memberService.getMemberParam(req);
		
		//아이디 중복여부 조회
		Map<String, Object> checkResult = memberService.idCheck(idCheckParam);

		//조회된 결과값 확인
		System.out.println(checkResult);
		
		//AJAX를 json 방식으로 응답하고, 응답을 위해 GSON API를 사용
		resp.setContentType("application/json");
		resp.getWriter().append( new Gson().toJson( checkResult ) );
	}
	
}
