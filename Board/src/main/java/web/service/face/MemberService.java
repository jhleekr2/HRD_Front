package web.service.face;

import javax.servlet.http.HttpServletRequest;

import web.dto.Member;

public interface MemberService {

	// 첫번째로 인터페이스에 메소드 자동완성할 때는 앞에 반드시 public 식별자 붙인다
	//(안그러면 실행에 문제 발생함)
	/**
	 * 로그인 정보가 담긴 전달파라미터를 추출한다
	 * @param req - 요청 정보 객체
	 * @return 사용자가 입력한 로그인 정보 객체
	 */
	public Member getLoginParam(HttpServletRequest req);
	
	/**
	 * 로그인 인증 처리하기
	 * @param param - 로그인 처리에 필요한 아이디/패스워드
	 * @return 로그인 성공 - true
	 *         로그인 실패 - false
	 */
	public boolean login(Member param);

	/**
	 * ID를 이용해 유저 정보 가져오기
	 * @param param - 유저정보 가져오는데 필요한 아이디/패스워드
	 * @return 유저정보를 담은 DTO객체
	 */
	public Member info(Member param);

	
	/**
	 * 회원가입 정보가 담긴 전달파라미터를 추출한다
	 * @param req - 요청 정보 객체
	 * @return 사용자가 입력한 회원가입 정보 객체
	 */
	public Member getJoinMember(HttpServletRequest req);

	/**
	 * 회원가입 처리하기
	 * @param param - 회원가입 처리에 필요한 아이디/패스워드/닉네임
	 */
	public void join(Member param);


}
