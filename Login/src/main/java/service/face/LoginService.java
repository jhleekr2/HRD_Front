package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.Login;

public interface LoginService {

	/**
	 * 로그인 정보가 담긴 전달파라미터를 추출한다
	 * @param req - 요청 정보 객체
	 * @return 사용자가 입력한 로그인 정보 객체
	 */
	public Login getLoginParam( HttpServletRequest req);

	/**
	 * 로그인 인증 처리하기
	 * @param param - 로그인 처리에 필요한 아이디/패스워드
	 * @return 로그인 성공 - 조회된 로그인 객체 정보
	 * 		로그인 실패 - null
	 */
	public Login loginProcess(Login param); 
}
