package web.dao.face;

import web.dto.Member;

public interface MemberDao {
	
	/**
	 * 테이블에 일치하는 아이디와 패스워드가 일치하는
	 * 사용자 정보가 있는지 확인한다
	 * count() 함수를 이용하여 구현
	 * 
	 * @param param - 조회할 아이디와 패스워드를 가진 DTO 객체
	 * @return 0-조회결과없음, 1-조회결과있음
	 */
	public int selectCntMemberByUseridUserpw(Member param);

	/**
	 * 아이디와 패스워드가 일치하는 사용자 정보를 불러온다
	 * @param param - 불러올 아이디와 패스워드를 가진 DTO객체
	 * @return - 사용자 정보를 포함한 DTO 객체
	 */
	public Member selectMemberByUserid(Member param);

	/**
	 * 아이디와 패스워드, 닉네임을 가진 사용자 정보를 등록한다
	 * @param param - 등록할 아이디와 패스워드, 닉네임을 가진 DTO객체
	 */
	public void insert(Member param);


}
