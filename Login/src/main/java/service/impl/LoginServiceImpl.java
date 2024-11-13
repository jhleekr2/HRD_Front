package service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dao.face.LoginDao;
import dto.Login;
import mybatis.MyBatisConnectionFactory;
import service.face.LoginService;

public class LoginServiceImpl implements LoginService {

	private SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
	
	private LoginDao loginDao;
	@Override
	public Login getLoginParam(HttpServletRequest req) {
		
		Login param = new Login();
		param.setLoginid( req.getParameter("loginid") );
		param.setLoginpw( req.getParameter("loginpw") );
		
		return param;
	}

	@Override
	public Login loginProcess(Login param) {
		
		SqlSession sqlSession = sqlSessionFactory.openSession();
		loginDao = sqlSession.getMapper(LoginDao.class);
		//------------------------------------------------------------
		
		// ID/PW 이용하여 회원 조회 (없으면 null)
		Login loginResult = loginDao.selectLogin( param );
		
		//------------------------------------------------------------
		sqlSession.close();
		
		return loginResult;
	}

}
