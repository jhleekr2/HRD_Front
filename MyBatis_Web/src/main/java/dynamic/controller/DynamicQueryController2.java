package dynamic.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dynamic.dao.DynamicQueryDao;
import mybatis.MyBatisConnectionFactory;

@WebServlet("/dynamic/query2")
public class DynamicQueryController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//SqlSession 객체를 생성할 팩토리 객체
	private SqlSessionFactory factory = MyBatisConnectionFactory.getSqlSessionFactory();
	private SqlSession sqlSession;
	
	//DAO 객체
	private DynamicQueryDao dynamicQueryDao;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/dynamic/query2 [GET]");

		//----------------------------------------------------------------------------

		sqlSession = factory.openSession();
		dynamicQueryDao = sqlSession.getMapper(DynamicQueryDao.class);
		//마이바티스 기본세팅 - 여기까지 세팅후 에러가 없어야 한다
		//----------------------------------------------------------------------------
		
		//7521 WARD
		HashMap<String, Object> map = dynamicQueryDao.selectByEmpno(7521);
		System.out.println( map );
		
		//모델값 전달
		req.setAttribute("map", map);
		
		//View 포워드
		req.getRequestDispatcher("/WEB-INF/views/dynamic/update.jsp").forward(req, resp);
	
		//---------------------------------------------------------
		sqlSession.close();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/dynamic/query2 [POST]");
		//---------------------------------------------------------
		
		sqlSession = factory.openSession();
		dynamicQueryDao = sqlSession.getMapper(DynamicQueryDao.class);
		
		//---------------------------------------------------------
		
		//전달파라미터 추출
		HashMap<String, Object> param = new HashMap<>();
		
		param.put("empno", req.getParameter("empno"));
		param.put("job", req.getParameter("job"));
		param.put("sal", req.getParameter("sal"));
		param.put("comm", req.getParameter("comm"));
		
		System.out.println("param : " + param);
		//---------------------------------------------------------

		//DB 업데이트 수행
		dynamicQueryDao.update( param );
		sqlSession.commit();
		//---------------------------------------------------------
		
		resp.sendRedirect("/dynamic/query2");
		
		//---------------------------------------------------------
		sqlSession.close();
		
	}
}
