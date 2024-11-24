package controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import service.face.EmpService;
import service.impl.EmpServiceImpl;

@WebServlet("/emp/detail")
public class EmpDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//컨트롤러에서 서비스 연결
	private EmpService empService = new EmpServiceImpl();
	
	

}
