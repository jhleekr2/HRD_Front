package dao.face;

import java.util.List;

import dto.Dept;

public interface DeptDao {
	//Mybatis가 DeptDaoImpl클래스를 자동으로 만들어 줄 것이다

	/**
	 * 전체 조회
	 * 
	 * @return 조회된 전체 부서 목록
	 */
	public List<Dept> selectAll();
	
	/**
	 * 부서 번호를 이용하여 특정 부서의 정보를 조회한다
	 * 
	 * @param deptno - 조회할 부서 번호
	 * @return 조회된 부서의 모든 컬럼 정보
	 */
	public Dept selectByDeptno(int deptno);
	
	/**
	 * 부서 이름을 이용한 조회
	 * 
	 * @param dname - 조회할 부서명
	 * @return 조회된 부서 정보 DTO 객체
	 */
	public Dept selectByDname(String dname);
	
	/**
	 * 부서 이름을 이용한 조회
	 * 
	 * @param data - 조회할 부서명
	 * @return 조회된 부서 정보 DTO 객체
	 */
	public Dept selectByDeptDname(Dept data);
	
	
	/**
	 * 새로운 부서 정보를 삽입한다
	 * 
	 * @param input - 삽입할 부서 정보
	 * @return INSERT 수행 결과 (영향받은 행 수)
	 */
	public int insertDept(Dept input);
	//DAO에서는 int나 void 쓰면 된다
	
}
