package service.face;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dto.Member;

public interface MemberService {

//join() 메소드 구현
	
	/**
	 * 회원가입 전달 파라미터를 추출한다
	 * 
	 * @param req - 요청 정보 객체
	 * @return 전달 파라미터 정보를 저장한 DTO 객체
	 */
	public Member getMemberParam(HttpServletRequest req);

	/**
	 * 회원가입 처리하기
	 * @param param - 회원가입에 사용할 회원 정보 DTO
	 */
	public void join(Member param);

	/**
	 * 전달된 아이디의 중복 체크를 진행한다
	 * 
	 * @param idCheckParam - 중복 체크할 아이디가 저장된 DTO 객체
	 * @return 아이디의 중복 여부
	 *  key = "duplicate"
	 *  value = true|false
	 *  
	 *  {"duplicate":true} - 중복있음
	 *  {"duplicate":false} - 중복없음
	 *  
	 */
	public Map<String, Object> idCheck(Member idCheckParam);

}
