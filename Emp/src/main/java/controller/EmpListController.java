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

@WebServlet("/emp/list")
public class EmpListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체
	private EmpService empService = new EmpServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/emp/list [GET]");
		
		//Emp 테이블의 전체 목록 조회 - EmpService 객체 이용
		List<Emp> list = empService.listAll();
		
		//조회 결과 테스트
		for( Emp e : list ) {
			System.out.println("EmpController doGet() - " + e);
		}
		
		//조회 결과(List<Emp>)를 View(JSP파일)에 전달
//		req.setAttribute("전달할 변수의 이름", 전달할 객체);
		//조회 결과 전달은 여러 차례에 걸쳐 전달될 수 있다
		req.setAttribute("eList", list);
		
		//View(JSP파일)을 지정하고 화면 전환(응답으로 보냄)
		req.getRequestDispatcher("/WEB-INF/views/emp/list.jsp").forward(req, resp);
	}
}
