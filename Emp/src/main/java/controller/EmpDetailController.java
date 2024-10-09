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

	//서비스 객체
	private EmpService empService = new EmpServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// req = 시작지점, resp = 끝지점
		System.out.println("/emp/detail [GET]");
		// ------------------------------------------------------------
		
		// 서블릿이 컨트롤러 역할을 하고, 서비스 EmpService에서 각종 기능을 수행
		
		// DTO 객체를 Service에 detail 메소드로 전송해야 한다
		
		//전달 파라미터 추출
		Emp paramEmp = empService.getParamEmpno(req);
		System.out.println("EmpDetailController - paramEmp : " + paramEmp);
		
		//사원 정보 상세 조회
		Emp emp = empService.detail(paramEmp);
		System.out.println("EmpDetailController - emp : " + emp);
		
		// ------------------------------------------------------------
		
		//클라이언트(브라우저)에서 보여줄 정보(DTO객체)를
		//View(JSP파일)에 전달
		req.setAttribute("result", emp);
		
		//JSP를 View로 지정하고 포워딩
		req.getRequestDispatcher("/WEB-INF/views/emp/detail.jsp").forward(req, resp);
	}

}
