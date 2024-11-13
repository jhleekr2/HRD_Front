package dao.face;

import dto.Login;

public interface LoginDao {

	/**
	 * 아이디와 패스워드가 일치하는 사용자 정보 조회하기
	 * 
	 * @param param - DB에서 조회할 아이디와 패스워드를 가진 객체
	 * @return 조회된 회원 정보, 없으면 null
	 */
	public Login selectLogin(Login param);

}
