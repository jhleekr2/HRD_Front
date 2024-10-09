package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.face.EmpService;
import service.impl.EmpServiceImpl;

@WebServlet("/emp/list")
public class EmpListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//서비스 객체
	private EmpService empService = new EmpServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
}
