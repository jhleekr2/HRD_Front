package service.impl;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;

import common.JDBCTemplate;
import dao.face.MemberDao;
import dao.impl.MemberDaoImpl;
import dto.Member;
import service.face.MemberService;

public class MemberServiceImpl implements MemberService{

	//DAO 객체(서비스 객체 선언)
	private MemberDao memberDao = new MemberDaoImpl();
	
	@Override
	public Member getParam(HttpServletRequest req) {
		String userid = req.getParameter("userid");
		String nick = req.getParameter("nick");
		String email = req.getParameter("email");
		
		Member param = new Member(0, userid, nick, email);
		System.out.println("MemberService param - " + param);
		
		return param;
	}

	@Override
	public Member join(Member param) {
		
		// DB 연결 객체
		Connection conn = JDBCTemplate.getConnection();
		
		//시퀀스(member_seq)를 이용하여 nextval을 조회(SELECT)한다
		//이때, DAO 이용한다.
		int nextval = memberDao.selectNextUserno(conn);

		//회원가입 데이터(param)에 nextval을 userno로 대입한다
		param.setUserno(nextval);
		System.out.println("MemberService join() - param : " + param);
		
		//DB에 회원 가입 정보 삽입(INSERT)
		//dao이용
		int res = memberDao.insertMember( conn, param );
		
		//트랜잭션 관리
		if(res > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		//DB에서 회원 가입 정보 조회(SELECT)
		// -> userno를 이용
		//dao이용
		Member member = memberDao.selectByUserno( conn, param.getUserno() );
		
		//DB에서 회원 가입 정보 조회 부분은 생략해도 사실 괜찮다
		
		return member;
	}

}
