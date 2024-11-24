package dao.face;

import dto.Member;

public interface MemberDao {

//	insertMember() 메소드 구현
	/**
	 * 회원 정보 삽입하기
	 * 
	 * @param param - 삽입할 DTO객체
	 * @return INSERT 수행 결과
	 */
	public int insert(Member param);

	/**
	 * 테이블에 기존 아이디가 있는 지 확인한다
	 * count() 함수를 이용하여 구현
	 * @param idCheckParam - 조회할 memberId를 가진 DTO 객체
	 * @return 0-조회결과 없음, 1-조회결과 있음
	 */
	public int selectCntMemberId(Member idCheckParam);

}
