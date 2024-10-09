package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Emp;

public interface EmpDao {

	/**
	 * 테이블 전체 조회
	 * 
	 * @param conn - DB연결 객체
	 * @return 조회된 Emp테이블의 전체 행 목록
	 */
	public List<Emp> selectAll(Connection conn);

	/**
	 * 주어진 empno에 해당하는 사원 조회하기
	 * 
	 * @param conn - DB 연결 객체
	 * @param paramEmp - empno를 가진 DTO 객체
	 * @return 조회된 사원 정보, 없으면 null
	 */
	public Emp selectByEmpno(Connection conn, Emp paramEmp);

}
