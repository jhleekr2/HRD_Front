package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Emp;
import service.face.EmpService;
import service.impl.EmpServiceImpl;

@WebServlet("/emp/detail")
public class EmpDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//컨트롤러에서 서비스 연결
	private EmpService empService = new EmpServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 콘솔에서 접속여부 확인
		System.out.println("/emp/detail [GET]");
		
		//전달 파라미터 얻기 - EmpService 객체 이용
		Emp param = empService.getParamEmpno( req );
		System.out.println("EmpDetailController doGet() - empno : " + param);
		
		//상세보기 조회 - EmpService 객체 이용
		Emp result = empService.getEmpDetail(param);
		System.out.println("EmpDetailController - result : " + result);
		
		//모델값 전달 - req 이용
		req.setAttribute("detail", result);
		
		//View 포워드 - req 이용
		req.getRequestDispatcher("/WEB-INF/views/emp/detail.jsp").forward(req, resp);
	}

}
