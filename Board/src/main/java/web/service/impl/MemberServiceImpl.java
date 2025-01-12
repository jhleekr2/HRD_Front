package web.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.MyBatisConnectionFactory;
import web.dao.face.MemberDao;
import web.dto.Member;
import web.service.face.MemberService;

public class MemberServiceImpl implements MemberService {
	
	//SqlSession객체를 생성할 팩토리 객체
	private SqlSessionFactory sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();

	//DAO 객체 - DAO 연결
	private MemberDao memberDao;
	
	@Override
	public Member getLoginParam(HttpServletRequest req) {
		
		//DTO 객체 정의
		Member param = new Member();
		
		//전달파라미터를 DTO에 저장하기
		param.setUserid( req.getParameter("loginid") );
		param.setUserpw( req.getParameter("loginpw") );
		
		//DTO 객체 반환
		return param;
	}
	
	@Override
	public boolean login(Member param) {
		//SQL 접속
		SqlSession sqlSession = sqlSessionFactory.openSession();

		//DAO Mapper 연결
		memberDao = sqlSession.getMapper(MemberDao.class);
		
		//true/false 반환위한 변수 정의
		int res = 0;
		
		//DAO를 통해 DB호출
		res = memberDao.selectCntMemberByUseridUserpw( param );
		System.out.println(res);
		
		//마이바티스 객체 자원 해제
		sqlSession.close();
		
		//반환값이 0이면 일치하는 로그인 정보가 없으며
		//0이 아니면(1) 일치하는 로그인 정보가 있는 것으로 간주한다.
		if(res != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Member info(Member param) {
		//SQL 접속
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//DAO Mapper 연결
		memberDao = sqlSession.getMapper(MemberDao.class);
		
		//DAO를 통해 DB호출
		param = memberDao.selectMemberByUserid( param );
		
		//마이바티스 객체 자원 해제
		sqlSession.close();
		
		//사용자 정보 반환
		return param;
	}

	@Override
	public Member getJoinMember(HttpServletRequest req) {
		
		//DTO 객체 정의
		Member param = new Member();
		
		//전달파라미터를 DTO에 저장하기
		param.setUserid( req.getParameter("joinid") );
		param.setUserpw( req.getParameter("joinpw") );
		param.setUsernick( req.getParameter("nickname") );

		//DTO 객체 반환
		return param;
	}

	@Override
	public void join(Member param) {
		
		//SQL 접속
		SqlSession sqlSession = sqlSessionFactory.openSession();
		
		//DAO Mapper 연결
		memberDao = sqlSession.getMapper(MemberDao.class);
		
		//DAO를 통해 DB호출
		memberDao.insert( param );
		
		//사실 트랜잭션 관리 부분을 좀더 조건 따져가면서 깊게 구현하고
		//만들려는 아이디가 중복인지 검증하는 코드가 추가적으로 필요하다
		//트랜잭션 관리
		sqlSession.commit();
		
		//마이바티스 객체 자원 해제
		sqlSession.close();
	}

}
