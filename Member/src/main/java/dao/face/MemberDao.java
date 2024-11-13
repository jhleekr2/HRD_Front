package dao.face;

import java.sql.Connection;

import dto.Member;

public interface MemberDao {

	/**
	 * member_seq의 nextval을 조회하여 반환한다
	 * 회원가입의 userno값으로 활용한다
	 * 
	 * @param conn - DB 연결 객체
	 * @return member_seq.nextval을 조회한 값
	 */
	public int selectNextUserno(Connection conn);

	/**
	 * 전달된 파라미터를 테이블에 삽입한다
	 * 
	 * @param conn - DB 연결 객체
	 * @param param - DB에 삽입할 정보 DTO 객체
	 * @return insert 수행 결과
	 */
	public int insertMember(Connection conn, Member param);

	/**
	 * userno를 이용하여 데이터 조회하기
	 * 
	 * @param conn - DB연결 객체
	 * @param userno - 조회할 PK값
	 * @return 조회된 정보 DTO객체, 없으면 null
	 */
	public Member selectByUserno(Connection conn, int userno);

}
