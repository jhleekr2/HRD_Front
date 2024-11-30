package settings.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.MyBatisConnectionFactory;
import settings.dao.face.TestMemberDao;
import settings.dto.TestMember;

@WebServlet("/null/test")
public class NullTestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SqlSessionFactory factory = MyBatisConnectionFactory.getSqlSessionFactory();
       
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/null/test [GET]");
//		BindingException - mapper 등록했는가, DAO랑 Mapper랑 연결되어있는가, 메소드가 잘 연결되어 있는가? 확인
		
		SqlSession session = factory.openSession();
		TestMemberDao dao = session.getMapper(TestMemberDao.class);
		
		//DB삽입 테스트
		TestMember tm = new TestMember(0, null, null);
		
		//DB삽입 수행
		dao.insert(tm);
		
		//커밋
		session.commit();
		
	}

}
