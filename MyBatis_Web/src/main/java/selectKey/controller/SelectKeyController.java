package selectKey.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.MyBatisConnectionFactory;
import selectKey.dao.SelectKeyDao;
import settings.dto.TestMember;

@WebServlet("/mybatis/selectKey")
public class SelectKeyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SqlSessionFactory factory = MyBatisConnectionFactory.getSqlSessionFactory();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mybatis/selectKey [GET]");
		// 콘솔에서 접속 확인하기
		
		//View 포워드 - req 이용
		req.getRequestDispatcher("/WEB-INF/views/mybatis/selectKey.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/mybatis/selectKey [POST]");
		//콘솔에서 접속 확인하기
		
		//이번 문제는 서비스를 따로 만들지 않고 컨트롤러에서 바로 처리하는 형태로 만들면 된다
		
		//전달파라미터를 DTO에 저장하기
		TestMember tmParam = new TestMember();
		tmParam.setId( req.getParameter("id") );
		tmParam.setPw( req.getParameter("pw") );
		
		System.out.println("SelectKeyController doPost() - tmParam : " + tmParam);
		//콘솔에서 전달값 확인하기
		SqlSession sqlSession = factory.openSession();
		SelectKeyDao dao = sqlSession.getMapper(SelectKeyDao.class);

		System.out.println("INSERT 수행 전 : " + tmParam);
		
		//SQL INSERT 수행
		dao.insert( tmParam );
		sqlSession.commit();
		
		System.out.println("INSERT 수행 후 : " + tmParam);
		
		//** selectKey를 통해 반환받은 PK값을 이용하여 추가 작업이 가능하다
//		resp.sendRedirect("./info?no=" + tmParam.getNo());
	}
}
