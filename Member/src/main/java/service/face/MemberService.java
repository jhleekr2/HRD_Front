package service.face;

import javax.servlet.http.HttpServletRequest;

import dto.Member;

public interface MemberService {

	/**
	 * 전달파라미터 req를 꺼내서 Member객체에 저장한다 
	 * 
	 * @param req - 서블릿 요청 정보 객체
	 * @return 요청 파라미터를 저장한 DTO객체
	 */
	public Member getParam(HttpServletRequest req);

	/** 
	 * DTO 객체 paramMbr을 join 메소드로 전송
	 * 
	 * @param paramMbr - SQL에 추가하게 될 DTO객체
	 */
	public Member join(Member param);

}
