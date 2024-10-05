package dao.face;

import java.sql.Connection;
import java.util.List;

import dto.Dept;

public interface DeptDao {

	/**
	 * 전체 부서 목록 조회하기
	 * 
	 * @param conn - DB연결 객체
	 * @return 조회된 전체 부서 목록
	 */
	//접근제한자 리턴타입 메소드의 이름(전달인자 - 자료형 변수명)
	//자료형 알려면 자료형 클릭하고 Ctrl+Enter 하면 알려준다
	public List<Dept> selectAll(Connection conn);

	/**
	 * 전달받은 DTO객체를 이용하여 DB에 INSERT한다
	 * 
	 * @param conn - DB연결 객체
	 * @param newDept - 새로 삽입할 부서 정보
	 * @return DML 수행 결과
	 */
	public int insertDept(Connection conn, Dept newDept);

}
