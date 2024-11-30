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

import dto.Emp;
import dynamic.dao.DynamicQueryDao;
import mybatis.MyBatisConnectionFactory;

@WebServlet("/dynamic/query")
public class DynamicQueryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//SqlSession 객체를 생성할 팩토리 객체
	private SqlSessionFactory factory = MyBatisConnectionFactory.getSqlSessionFactory();
	private SqlSession sqlSession;
	
	//DAO 객체
	private DynamicQueryDao dynamicQueryDao;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("/dynamic/query [GET]");

		//----------------------------------------------------------------------------

		sqlSession = factory.openSession();
		dynamicQueryDao = sqlSession.getMapper(DynamicQueryDao.class);
		//마이바티스 기본세팅 - 여기까지 세팅후 에러가 없어야 한다
		//----------------------------------------------------------------------------
		//DTO 필드 -> HashMap Entry
		//자료형 변수명; ->: HashMap<String, Object>
		
		List<HashMap<String, Object>> list = dynamicQueryDao.select1();
		//HashMap 하나가 한 행을 저장하며 DTO의 역할을 대체한다
		//그것을 다시 List 형태로 변환한다
		for( HashMap<String, Object> m : list ) {
			System.out.println( m );
//			System.out.println("---------------------");
			//이때 HashMap에서 empno, ename 꺼내는 방법?
			//** 주의! 조회 결과 컬럼이 대문자로 구성되어있다
			// -> 키 값을 대문자로 작성해야 한다
			System.out.println( m.get("EMPNO") + " : " + m.get("ENAME"));			
		}
		//좀더 어렵지만 DTO보다 효율적. 하지만 단점으로는 뭐가 들어올지 예상을 하지 못한다.
		
		//----------------------------------------------------------------------------
		System.out.println("-----------------");
		
		String data = null;
		
		data = "ER";
//		data = "A";
//		data = "";
//		data = null;
		list = dynamicQueryDao.select2( data );
		
		//----------------------------------------------------------------------------

		Emp emp = new Emp();
		emp.setEmpno( 7839 ); //KING
		
		list = dynamicQueryDao.select3( emp );
		//----------------------------------------------------------------------------
		HashMap<String, Object> param = new HashMap<>();
		
		param.put("category", req.getParameter("category"));
		param.put("keyword", req.getParameter("keyword"));
		
		list = dynamicQueryDao.select4( param );
		
		//----------------------------------------------------------------------------
		req.setAttribute("list", list);
		req.getRequestDispatcher("/WEB-INF/views/dynamic/query.jsp").forward(req, resp);
		
	}

}
