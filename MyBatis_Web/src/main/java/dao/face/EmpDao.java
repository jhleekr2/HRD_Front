package dao.face;

import java.util.List;

import dto.Emp;

public interface EmpDao {

	/**
	 * 전체 조회하기
	 * 
	 * @return 조회된 전체 결과 리스트
	 */
	public List<Emp> selectAll();

	/**
	 * 특정 사번의 결과 조회하기
	 * 
	 * @param empno 조회하고자 하는 사원의 사번
	 * @return 조회된 결과값
	 */
	public Emp selectByEmpno(Emp param);

}
