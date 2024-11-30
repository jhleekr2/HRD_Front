package settings.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.MyBatisConnectionFactory;
import settings.dao.face.UnderscoreDao;
import settings.dto.Underscore;

@WebServlet("/map/underscore")
public class MapUnderscoreController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private SqlSessionFactory factory = MyBatisConnectionFactory.getSqlSessionFactory();
	
	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/map/underscore [GET]");
		
		UnderscoreDao dao = factory.openSession()
								.getMapper(UnderscoreDao.class);
		
		List<Underscore> list = dao.selectAll();
		for(Underscore u : list) System.out.println(u);
		
	}
}
