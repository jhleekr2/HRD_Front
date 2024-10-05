package service.face;

import java.util.List;

import dto.Dept;

public interface DeptService {

	/**
	 * 전체 부서 목록을 조회한다
	 * 
	 * @return 조회된 전체 부서 목록
	 */
	public List<Dept> listAll();

	/**
	 * 새로운 부서 정보를 Scanner를 이용하여 사용자에게 입력받는다
	 * 
	 * @return 입력받은 부서 정보를 담은 DTO객체
	 */
	public Dept inputDept();

	/**
	 * 새로운 부서 정보를 DB에 등록한다
	 * 
	 * @param newDept - 새롭게 추가될 부서 정보 DTO객체
	 * @return 데이터 삽입 성공/실패
	 */
	public boolean register(Dept newDept);


}
