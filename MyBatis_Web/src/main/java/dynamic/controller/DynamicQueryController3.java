package dynamic.controller;

import java.io.IOException;
import java.util.Arrays;
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

@WebServlet("/dynamic/query3")
public class DynamicQueryController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//SqlSession 객체를 생성할 팩토리 객체
	private SqlSessionFactory factory = MyBatisConnectionFactory.getSqlSessionFactory();
	private SqlSession sqlSession;
	
	//DAO 객체
	private DynamicQueryDao dynamicQueryDao;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/dynamic/query3 [GET]");
		//---------------------------------------------------------
		
		req.getRequestDispatcher("/WEB-INF/views/dynamic/queryCheckbox.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/dynamic/query3 [POST]");
		//---------------------------------------------------------
		sqlSession = factory.openSession();
		dynamicQueryDao = sqlSession.getMapper(DynamicQueryDao.class);
		//---------------------------------------------------------
		String[] deptnos = req.getParameterValues("deptno");
		System.out.println( Arrays.toString(deptnos));
		
		//---------------------------------------------------------
		HashMap<String, Object> param = new HashMap<>();
		param.put("deptnos", deptnos);
		System.out.println("param : " + param);
		//여기서 param의 toString이 재정의되어있지 않음
		
		//---------------------------------------------------------
		List<HashMap<String, Object>> list = dynamicQueryDao.selectCheckbox( param );
		for( HashMap<String, Object> m : list ) {
			System.out.println( m );
		}
		
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/views/dynamic/queryCheckboxResult.jsp").forward(req, resp);
		
		//---------------------------------------------------------
		sqlSession.close();
	}
}
