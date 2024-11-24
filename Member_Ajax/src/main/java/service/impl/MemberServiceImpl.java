package service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dao.face.MemberDao;
import dto.Member;
import mybatis.MyBatisConnectionFactory;
import service.face.MemberService;

public class MemberServiceImpl implements MemberService {

	//마이바티스 연결 객체
	//-> MyBatis를 싱글톤으로 만들었음(JDBC를 대신한다)
	private SqlSessionFactory factory = MyBatisConnectionFactory.getSqlSessionFactory();
	
	//DAO 연결 객체
	private MemberDao memberDao;
	
	
	@Override
	public Member getMemberParam(HttpServletRequest req) {

		//입력 값을 받기 위해 Member 타입의 참조형 변수를 정의한다
		Member param = new Member();
		
		//정의된 변수에 입력값을 대입하여 넘긴다 - 회원가입 전달 파라미터 추출
		param.setMemberId( req.getParameter("memberId") );
		param.setMemberPw( req.getParameter("memberPw") );
		param.setMemberNick( req.getParameter("memberNick") );
		param.setMemberPhone( req.getParameter("memberPhone") );
		param.setMemberPostcode( req.getParameter("memberPostcode") );
		param.setMemberAddr1( req.getParameter("memberAddr1") );
		param.setMemberAddr2( req.getParameter("memberAddr2") );
		return param;	
	}

	@Override
	public void join(Member param) {
		//마이바티스 수행 객체
		SqlSession sqlSession = factory.openSession();
		
		//DAO 객체 연결
		memberDao = sqlSession.getMapper(MemberDao.class);
		//여기서 통과 못하면 마이바티스 연결이 안되어 있다는 것을 의미
		
		//마이바티스를 통해 회원정보 추가
		int res = memberDao.insert(param);
		
		if( res > 0 ) {
			//회원정보 추가가 성공하면 커밋
			sqlSession.commit();
		} else {
			//회원정보 추가가 실패하면 롤백
			sqlSession.rollback();
		}
		
		//마이바티스 객체 자원 해제
		sqlSession.close();
	}

	@Override
	public Map<String, Object> idCheck(Member idCheckParam) {
		//마이바티스 수행 객체
		SqlSession sqlSession = factory.openSession();

		//DAO 객체 연결
		memberDao = sqlSession.getMapper(MemberDao.class);
		
		//마이바티스를 통해 회원정보 확인
		int cnt = memberDao.selectCntMemberId( idCheckParam );
		
		Map<String, Object> idCheckResult = new HashMap<>();
		
		if( cnt > 0 ) {
			//중복있음
			idCheckResult.put("duplicate", true);
		} else {
			//중복없음
			idCheckResult.put("duplicate", false);
		}
		
		//마이바티스 객체 자원 해제
		sqlSession.close();
		
		return idCheckResult;
	}	

}
