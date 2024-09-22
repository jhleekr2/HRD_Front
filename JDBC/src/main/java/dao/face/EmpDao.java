package dao.face;

import java.util.List;

import dto.Emp;

public interface EmpDao {

	/**
	 * 전체 행을 조회한다
	 * PK인 empno를 기준으로 정렬한다
	 * 
	 * @return List<Emp> - 테이블 전체 행을 조회한 결과 리스트 객체
	 */
	public List<Emp> selectAll();
	// 인터페이스 자동완성 후에는 public 반드시 명시한다
	// 안그러면 실행시 문제가 발생한다

	/**
	 * 지정된 부서 번호의 사원들을 조회한다
	 * 
	 * @param deptno - 조회하려는 부서번호
	 * @return 조회된 부서 번호의 사원들 목록 객체
	 */
	public List<Emp> selectByDeptno(int deptno);

	/**
	 * 새로운 사원 정보를 삽입한다
	 * 
	 * @param data - 새로 삽입할 사원 데이터
	 * @return 영향받은 행 수
	 */
	public int insertEmp(Emp data);

}
