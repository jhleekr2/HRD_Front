package service.face;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dto.Emp;

public interface EmpService {

	/**
	 * 사원의 전체 목록을 조회한다
	 * 
	 * @return 조회된 사원들의 객체 목록
	 */
	public List<Emp> listAll();
	//첫번째 자동 완성 후에는 반드시 public 붙여야 한다

	/**
	 * 전달파라미터 empno를 추출한다
	 * 
	 * @param req - 요청 정보 객체
	 * @return 요청 파라미터를 저장한 DTO객체
	 */
	public Emp getParamEmpno(HttpServletRequest req);

	/**
	 * 전달된 empno 을 이용하여 사원 정보를 조회한다
	 * 
	 * @param paramEmp - 조회할 사원의 empno를 가진 DTO 객체
	 * @return 조회된 사원의 상세 정보
	 */
	public Emp detail(Emp paramEmp);
	
	

}
