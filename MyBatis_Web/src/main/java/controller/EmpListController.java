package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Emp;
import service.face.EmpService;
import service.impl.EmpServiceImpl;

//@WebServlet("/EmpListController") -> URL규칙에 의해 /emp/list폴더를 URL로 지정
@WebServlet("/emp/list")
public class EmpListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//컨트롤러에서 서비스 연결
	private EmpService empService = new EmpServiceImpl();
	//무엇을 만들지 몰라도 연결이 되도록 만드는 것이 과제!
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/emp/list [GET]");
		//콘솔에서 접속되는지 여부 확인
		//----------------------------------------------------------------------

		//사원 전체 정보 조회 - EmpService 객체 이용
		List<Emp> list = empService.getList();
		
		//조회 결과를 모델값으로 전달하기 - req객체 이용
		req.setAttribute("list", list);
		
		//----------------------------------------------------------------------
		//JSP로 포워드
		req.getRequestDispatcher("/WEB-INF/views/emp/list.jsp").forward(req, resp);
	}
}
